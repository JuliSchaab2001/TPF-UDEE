package com.utn.TPFUDEE.Controllers;

import com.utn.TPFUDEE.Models.Meter;
import com.utn.TPFUDEE.Services.MeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meter")
public class MeterController {
    @Autowired
    private MeterService meterService;

    @GetMapping("/")
    public List<Meter> getAll(){
        return meterService.getAll();
    }

    @GetMapping("/{id}")
    public Meter getById(@PathVariable Integer id){
        return meterService.getById(id);
    }

    @PostMapping("/")
    public void add(@RequestBody Meter meter){
        meterService.add(meter);
    }
}