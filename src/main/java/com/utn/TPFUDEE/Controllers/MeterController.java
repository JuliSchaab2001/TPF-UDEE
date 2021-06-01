package com.utn.TPFUDEE.Controllers;

import com.utn.TPFUDEE.Exceptions.Exist.MeterExistException;
import com.utn.TPFUDEE.Exceptions.NotFound.MeterNotFoundException;
import com.utn.TPFUDEE.Models.Meter;
import com.utn.TPFUDEE.Services.MeterService;
import com.utn.TPFUDEE.Utils.EntityURLBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meter")
public class MeterController {

    private static final String METER_PATH = "meter";

    @Autowired
    private MeterService meterService;

    /*@GetMapping("/{id}")
    public ResponseEntity<Meter> getById(@PathVariable Integer id) throws MeterNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).header("Nombre", "Cuerpo").body(meterService.getById(id));
    }*/

    //agregar modificacion de medidor

    @PostMapping("/")
    public ResponseEntity add(@RequestBody Meter meter) throws MeterExistException {
        return ResponseEntity.status(HttpStatus.CREATED).location(EntityURLBuilder.buildURL(METER_PATH,meterService.add(meter).getMeterId())).build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) throws MeterNotFoundException {
        meterService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).header("Aca", "Salio todo en orden man").build();
    }

    //Agregar Modificaciones
    @PutMapping("/")
    public ResponseEntity modify(){
        return null;
    }
}