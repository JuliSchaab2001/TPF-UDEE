package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Exceptions.AddressExistException;
import com.utn.TPFUDEE.Models.Address;
import com.utn.TPFUDEE.Repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class AddressService {

    private AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAll(){
        return addressRepository.findAll();
    }

    public void add(Address address){
        addressRepository.save(address);
    }

    public Address getById(Integer id){
        return addressRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }


}
