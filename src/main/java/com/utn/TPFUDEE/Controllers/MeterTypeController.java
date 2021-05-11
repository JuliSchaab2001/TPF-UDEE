package com.utn.TPFUDEE.Controllers;

import com.utn.TPFUDEE.Services.MeterTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meterType")
public class MeterTypeController {
    @Autowired
    private MeterTypeService meterTypeService;

    @GetMapping("/")
    public List<MeterType > getAll(){
        return meterTypeService.getAll();
    }

    @GetMapping("/{id}")
    public MeterType getById(@PathVariable Integer id){
        return meterTypeService.getById(id);
    }

    @PostMapping("/")
    public void add(@RequestBody MeterType meterType){
        meterTypeService.add(meterType);
    }
}