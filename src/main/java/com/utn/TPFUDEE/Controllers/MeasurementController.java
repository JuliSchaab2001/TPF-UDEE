package com.utn.TPFUDEE.Controllers;


import com.utn.TPFUDEE.Exceptions.MeasurementExistException;
import com.utn.TPFUDEE.Models.Measurement;
import com.utn.TPFUDEE.Services.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {
    @Autowired
    private MeasurementService measurementService;

    @GetMapping("/")
    public ResponseEntity<List<Measurement>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).header("Nombre", "Cuerpo").body(measurementService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Measurement> getById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).header("Nombre", "Cuerpo").body(measurementService.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity add(@RequestBody Measurement measurement) throws MeasurementExistException {
        measurementService.add(measurement);
        return ResponseEntity.status(HttpStatus.ACCEPTED).header("Cuerpo", "Aca habria que poner un header location").build();
    }
}