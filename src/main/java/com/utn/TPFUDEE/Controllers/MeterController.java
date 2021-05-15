package com.utn.TPFUDEE.Controllers;


import com.utn.TPFUDEE.Exceptions.Exist.MeterExistException;
import com.utn.TPFUDEE.Exceptions.NoContent.MeterNoContentException;
import com.utn.TPFUDEE.Exceptions.NotFound.MeterNotFoundException;
import com.utn.TPFUDEE.Exceptions.NotFound.UserNotFoundException;
import com.utn.TPFUDEE.Models.Meter;
import com.utn.TPFUDEE.Services.MeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meter")
public class MeterController {
    @Autowired
    private MeterService meterService;

    @GetMapping("/")
    public ResponseEntity<List<Meter>> getAll() throws MeterNoContentException {
        return ResponseEntity.status(HttpStatus.OK).header("Nombre", "Cuerpo").body(meterService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meter> getById(@PathVariable Integer id) throws MeterNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).header("Nombre", "Cuerpo").body(meterService.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity add(@RequestBody Meter meter) throws MeterExistException {
        meterService.add(meter);
        return ResponseEntity.status(HttpStatus.ACCEPTED).header("Cuerpo", "Aca habria que poner un header location").build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) throws MeterNotFoundException {
        meterService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).header("Aca", "Salio todo en orden man").build();
    }
}