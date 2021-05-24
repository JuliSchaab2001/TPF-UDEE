package com.utn.TPFUDEE.Controllers;

import com.utn.TPFUDEE.Exceptions.Exist.AddressExistException;
import com.utn.TPFUDEE.Exceptions.NoContent.AddressNoContentException;
import com.utn.TPFUDEE.Exceptions.NotFound.AddressNotFoundException;
import com.utn.TPFUDEE.Models.Address;
import com.utn.TPFUDEE.Services.AddressService;
import com.utn.TPFUDEE.Utils.EntityURLBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private static final String ADDRESS_PATH = "address";

    @Autowired
    private AddressService addressService;

    @GetMapping("/")
    public ResponseEntity<List<Address>> getAll(Pageable pageable) throws AddressNoContentException {
        Page<Address> p = addressService.getAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).header("X-Total-Count", Long.toString(p.getTotalElements())).header("X-Total-Pages", Long.toString(p.getTotalPages())).body(p.getContent());}

    @GetMapping("/{id}")
    public ResponseEntity<Address> getById(@PathVariable Integer id) throws AddressNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).header("nombre", "Cuerpo").body( addressService.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity add(@RequestBody Address address) throws AddressExistException {
        return ResponseEntity.status(HttpStatus.CREATED).location(EntityURLBuilder.buildURL(ADDRESS_PATH, addressService.add(address).getAddress_id())).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) throws AddressNotFoundException {
        addressService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).header("Aca", "Salio todo en orden man").build();
    }
}