package com.utn.TPFUDEE.Controllers;

import com.utn.TPFUDEE.Models.Address;
import com.utn.TPFUDEE.Services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/")
    public List<Address> getAll(){
        return addressService.getAll();

    }

    @GetMapping("/{id}")
    public Address getById(@PathVariable Integer id){
        return addressService.getById(id);
    }

    @PostMapping("/")
    public void add(@RequestBody Address address){
        addressService.add(address);
    }
}