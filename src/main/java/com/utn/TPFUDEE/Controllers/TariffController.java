package com.utn.TPFUDEE.Controllers;



import com.utn.TPFUDEE.Exceptions.Exist.TariffExistException;
import com.utn.TPFUDEE.Exceptions.NoContent.TariffNoContentException;
import com.utn.TPFUDEE.Exceptions.NotFound.TariffNotFoundException;
import com.utn.TPFUDEE.Models.Tariff;
import com.utn.TPFUDEE.Services.TariffService;
import com.utn.TPFUDEE.Utils.EntityURLBuilder;
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

    private static final String TARIFF_PATH = "tariff";

    @Autowired
    private TariffService tariffService;

    /*@GetMapping("/{id}")
    public ResponseEntity<Tariff> getById(@PathVariable Integer id) throws TariffNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).header("Nombre", "Cuerpo").body( tariffService.getById(id));
    }*/

    //Agregar Modificaciones
    @PutMapping("/")
    public ResponseEntity modify(){
        return null;
    }



    //Alta de TarifF
    @PostMapping("/")
    public ResponseEntity add(@RequestBody Tariff tariff) throws TariffExistException {
        return ResponseEntity.status(HttpStatus.CREATED).location(EntityURLBuilder.buildURL(TARIFF_PATH,tariffService.add(tariff).getTariff_id())).build();
    }


    //Baja de Tariff
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) throws TariffNotFoundException {
        tariffService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).header("Aca", "Salio todo en orden man").build();
    }
}
