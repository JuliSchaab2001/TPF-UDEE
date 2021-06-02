package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Models.Address;
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

    public void deleteById(Integer id){
        if((this.getById(id))!=null)
            addressRepository.deleteById(id);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Address Not Found For delete");
    }


}
