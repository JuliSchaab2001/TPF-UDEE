package com.utn.TPFUDEE.Controllers;

import com.utn.TPFUDEE.Exceptions.ExistsException;
import com.utn.TPFUDEE.Exceptions.MeterTypeExistException;
import com.utn.TPFUDEE.Models.MeterType;
import com.utn.TPFUDEE.Services.MeterTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meterType")
public class MeterTypeController {
    @Autowired
    private MeterTypeService meterTypeService;

    @GetMapping("/")
    public ResponseEntity<List<MeterType>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).header("Nombre", "Cuerpo").body(meterTypeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeterType> getById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).header("Nombre", "Cuerpo").body(meterTypeService.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity add(@RequestBody MeterType meterType) throws MeterTypeExistException {
        meterTypeService.add(meterType);
        return ResponseEntity.status(HttpStatus.ACCEPTED).header("Cuerpo", "Aca habria que poner un header location").build();
    }
}