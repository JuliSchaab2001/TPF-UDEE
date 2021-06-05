package com.utn.TPFUDEE.Controllers;

import com.utn.TPFUDEE.Models.Meter;
import com.utn.TPFUDEE.Models.Tariff;
import com.utn.TPFUDEE.Services.MeterService;
import com.utn.TPFUDEE.Utils.EntityURLBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.Authenticator;

@RestController
@RequestMapping("/meter")
public class MeterController {

    private static final String METER_PATH = "meter";

    @Autowired
    private MeterService meterService;
    //agregar modificacion de medidor

    @PostMapping("/")
    public ResponseEntity add(@RequestBody Meter meter){
        return ResponseEntity.status(HttpStatus.CREATED).location(EntityURLBuilder.buildURL(METER_PATH,meterService.add(meter).getMeterId())).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id){
        meterService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).header("Aca", "Salio todo en orden man").build();
    }

    @PutMapping("/")
    public ResponseEntity update(@RequestBody Meter meter){
        if(meterService.getById(meter.getMeterId()) != null)
            return ResponseEntity.status(HttpStatus.OK).location(EntityURLBuilder.buildURL(METER_PATH,meterService.update(meter).getMeterId())).build();
        else
            return this.add(meter);
    }
}