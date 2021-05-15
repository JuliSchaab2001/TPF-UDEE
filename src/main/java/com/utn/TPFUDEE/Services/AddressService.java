package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Exceptions.Exist.AddressExistException;
import com.utn.TPFUDEE.Exceptions.Exist.UserExistException;
import com.utn.TPFUDEE.Exceptions.NoContent.AddressNoContentException;
import com.utn.TPFUDEE.Exceptions.NotFound.AddressNotFoundException;
import com.utn.TPFUDEE.Models.Address;
import com.utn.TPFUDEE.Repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {

    private AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    public void add(Address address) throws AddressExistException {
        boolean flag = false;
        for(Address var : addressRepository.findAll()){
            if(address.getStreet().equals(var.getStreet()) && address.getNumber().equals(var.getNumber())){
                flag = true;
            }
        }
        if(flag){
            throw new AddressExistException();
        }else{
            addressRepository.save(address);
        }
    }


    public List<Address> getAll() throws AddressNoContentException{
        List<Address> addressList = addressRepository.findAll();
        if(addressList.isEmpty()){
            throw new AddressNoContentException();
        }
        return addressRepository.findAll();
    }


    public Address getById(Integer id) throws AddressNotFoundException{
        return addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException());
    }

    public void deleteById(Integer id) throws AddressNotFoundException{
        this.getById(id);
        addressRepository.deleteById(id);
    }


}
