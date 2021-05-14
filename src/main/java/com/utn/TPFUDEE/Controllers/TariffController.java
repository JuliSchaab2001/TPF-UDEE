package com.utn.TPFUDEE.Controllers;



import com.utn.TPFUDEE.Exceptions.TariffExistException;
import com.utn.TPFUDEE.Models.Tariff;
import com.utn.TPFUDEE.Services.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tariff")
public class TariffController {
    @Autowired
    private TariffService tariffService;

    @GetMapping("/")
    public ResponseEntity<List<Tariff>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).header("Nombre", "Cuerpo").body(tariffService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tariff> getById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).header("Nombre", "Cuerpo").body( tariffService.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity add(@RequestBody Tariff tariff) throws TariffExistException {
        tariffService.add(tariff);
        return ResponseEntity.status(HttpStatus.ACCEPTED).header("Cuerpo", "Aca habria que poner un header location").build();
    }
}
