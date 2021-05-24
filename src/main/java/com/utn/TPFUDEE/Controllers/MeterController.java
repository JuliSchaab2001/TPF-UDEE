package com.utn.TPFUDEE.Controllers;


import com.utn.TPFUDEE.Exceptions.Exist.MeterExistException;
import com.utn.TPFUDEE.Exceptions.NoContent.MeterNoContentException;
import com.utn.TPFUDEE.Exceptions.NotFound.MeterNotFoundException;
import com.utn.TPFUDEE.Models.Meter;
import com.utn.TPFUDEE.Services.MeterService;
import com.utn.TPFUDEE.Utils.EntityURLBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meter")
public class MeterController {

    private static final String METER_PATH = "meter";

    @Autowired
    private MeterService meterService;

    @GetMapping("/")
    public ResponseEntity<List<Meter>> getAll(Pageable pageable) throws MeterNoContentException {
        Page<Meter> p = meterService.getAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).header("X-Total-Count", Long.toString(p.getTotalElements())).header("X-Total-Pages", Long.toString(p.getTotalPages())).body(p.getContent());    }

    @GetMapping("/{id}")
    public ResponseEntity<Meter> getById(@PathVariable Integer id) throws MeterNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).header("Nombre", "Cuerpo").body(meterService.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity add(@RequestBody Meter meter) throws MeterExistException {
        return ResponseEntity.status(HttpStatus.CREATED).location(EntityURLBuilder.buildURL(METER_PATH,meterService.add(meter).getMeter_id())).build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) throws MeterNotFoundException {
        meterService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).header("Aca", "Salio todo en orden man").build();
    }
}