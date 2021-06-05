package com.utn.TPFUDEE.Controllers;

import com.utn.TPFUDEE.Models.Tariff;
import com.utn.TPFUDEE.Services.TariffService;
import com.utn.TPFUDEE.Utils.EntityURLBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/tariff")
public class TariffController {

    private static final String TARIFF_PATH = "tariff";

    @Autowired
    private TariffService tariffService;

    @PostMapping("/")
    public ResponseEntity add(@RequestBody Tariff tariff){
        return ResponseEntity.status(HttpStatus.CREATED).location(EntityURLBuilder.buildURL(TARIFF_PATH,tariffService.add(tariff).getTariffId())).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id){
        tariffService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).header("Aca", "Salio todo en orden man").build();
    }

    @PutMapping("/")
    public ResponseEntity update(@RequestBody Tariff tariff){
        if(tariffService.getById(tariff.getTariffId()) != null)
            return ResponseEntity.status(HttpStatus.OK).location(EntityURLBuilder.buildURL(TARIFF_PATH,tariffService.update(tariff).getTariffId())).build();
        else
            return this.add(tariff);
    }
}
