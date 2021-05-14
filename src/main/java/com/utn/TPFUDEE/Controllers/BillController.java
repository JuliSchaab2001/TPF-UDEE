package com.utn.TPFUDEE.Controllers;


import com.utn.TPFUDEE.Exceptions.BillExistException;
import com.utn.TPFUDEE.Exceptions.ExistsException;
import com.utn.TPFUDEE.Models.Bill;
import com.utn.TPFUDEE.Services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private BillService billService;

    @GetMapping("/")
    public ResponseEntity<List<Bill>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).header("Nombre", "Cuerpo").body(billService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bill> getById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).header("Nombre", "Cuerpo").body(billService.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity add(@RequestBody Bill bill) throws BillExistException {
        billService.add(bill);
        return ResponseEntity.status(HttpStatus.ACCEPTED).header("Cuerpo", "Aca habria que poner un header location").build();
    }
}