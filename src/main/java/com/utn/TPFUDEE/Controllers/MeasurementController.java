package com.utn.TPFUDEE.Controllers;

import com.utn.TPFUDEE.Services.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {
    @Autowired
    private MeasurementService measurementService;

    @GetMapping("/")
    public List<Measurement > getAll(){
        return measurementService.getAll();
    }

    @GetMapping("/{id}")
    public Measurement getById(@PathVariable Integer id){
        return measurementService.getById(id);
    }

    @PostMapping("/")
    public void add(@RequestBody Measurement measurement){
        measurementService.add(measurement);
    }
}