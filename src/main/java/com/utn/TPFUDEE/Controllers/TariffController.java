package com.utn.TPFUDEE.Controllers;



import com.utn.TPFUDEE.Exceptions.Exist.TariffExistException;
import com.utn.TPFUDEE.Exceptions.NoContent.TariffNoContentException;
import com.utn.TPFUDEE.Exceptions.NotFound.TariffNotFoundException;
import com.utn.TPFUDEE.Models.Tariff;
import com.utn.TPFUDEE.Services.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<List<Tariff>> getAll(Pageable pageable) throws TariffNoContentException {
        Page<Tariff> p = tariffService.getAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).header("X-Total-Count", Long.toString(p.getTotalElements())).header("X-Total-Pages", Long.toString(p.getTotalPages())).body(p.getContent());    }

    @GetMapping("/{id}")
    public ResponseEntity<Tariff> getById(@PathVariable Integer id) throws TariffNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).header("Nombre", "Cuerpo").body( tariffService.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity add(@RequestBody Tariff tariff) throws TariffExistException {
        tariffService.add(tariff);
        return ResponseEntity.status(HttpStatus.ACCEPTED).header("Cuerpo", "Aca habria que poner un header location").build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) throws TariffNotFoundException {
        tariffService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).header("Aca", "Salio todo en orden man").build();
    }
}
