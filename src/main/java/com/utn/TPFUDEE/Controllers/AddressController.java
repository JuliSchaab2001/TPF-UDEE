package com.utn.TPFUDEE.Controllers;

import com.utn.TPFUDEE.Exceptions.AddressExistException;
import com.utn.TPFUDEE.Exceptions.ExistsException;
import com.utn.TPFUDEE.Models.Address;
import com.utn.TPFUDEE.Services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/")
    public ResponseEntity<List<Address>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).header("Nombre", "Cuerpo").body(addressService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).header("nombre", "Cuerpo").body( addressService.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity add(@RequestBody Address address) throws AddressExistException {
        addressService.add(address);
        return ResponseEntity.status(HttpStatus.ACCEPTED).header("Cuerpo", "Aca habria que poner un header location").build();
    }
}