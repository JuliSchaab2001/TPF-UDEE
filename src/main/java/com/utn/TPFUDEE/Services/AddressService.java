package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Models.Address;
import com.utn.TPFUDEE.Models.Projections.AddressProjection;
import com.utn.TPFUDEE.Repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class AddressService {

    private AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Page<Address> getAll(Pageable pageable){
        Page<Address> addressList = addressRepository.findAll(pageable);
        if(addressList.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Empty List");
        }
        return addressList;
    }

    public Address add(Address address){
        if((addressRepository.findByStreetAndNumber(address.getStreet(), address.getNumber()))!=null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Address Already Exist");
        }else{
            return addressRepository.save(address);
        }
    }

    public Address getById(Integer id){
        return addressRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address Not Found"));
    }

    public AddressProjection getOnlyAddressById(Integer id){
        this.getById(id);
        return addressRepository.findById2(id);
    }

    public void deleteById(Integer id){
        this.getById(id);
        addressRepository.deleteById(id);
    }

    public Address update(Address address) {
        if(this.getById(address.getAddressId()) != null)
            return addressRepository.save(address);
        else
            return null;
    }


}
