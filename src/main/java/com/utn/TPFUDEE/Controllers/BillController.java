package com.utn.TPFUDEE.Controllers;


import com.utn.TPFUDEE.DTO.BillDTO;
import com.utn.TPFUDEE.Exceptions.Exist.BillExistException;
import com.utn.TPFUDEE.Exceptions.NoContent.BillNoContentException;
import com.utn.TPFUDEE.Exceptions.NotFound.BillNotFoundException;
import com.utn.TPFUDEE.Exceptions.NotFound.UserNotFoundException;
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
    public ResponseEntity<List<BillDTO>> getAll() throws BillNoContentException {
        return ResponseEntity.status(HttpStatus.OK).header("Nombre", "Cuerpo").body(BillDTO.mapBillListToDTO(billService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillDTO> getById(@PathVariable Integer id) throws BillNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).header("Nombre", "Cuerpo").body(BillDTO.mapBillToDTO(billService.getById(id)));
    }

    @PostMapping("/")
    public ResponseEntity add(@RequestBody Bill bill) throws BillExistException {
        billService.add(bill);
        return ResponseEntity.status(HttpStatus.ACCEPTED).header("Cuerpo", "Aca habria que poner un header location").build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) throws BillNotFoundException {
        billService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).header("Aca", "Salio todo en orden man").build();
    }
}