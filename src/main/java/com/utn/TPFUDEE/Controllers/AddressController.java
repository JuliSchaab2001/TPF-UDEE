package com.utn.TPFUDEE.Controllers;

import com.utn.TPFUDEE.Exceptions.Exist.AddressExistException;
import com.utn.TPFUDEE.Exceptions.NoContent.AddressNoContentException;
import com.utn.TPFUDEE.Exceptions.NotFound.AddressNotFoundException;
import com.utn.TPFUDEE.Models.Address;
import com.utn.TPFUDEE.Models.Projections.BillProjection;
import com.utn.TPFUDEE.Models.Projections.MeasurementProjection;
import com.utn.TPFUDEE.Services.AddressService;
import com.utn.TPFUDEE.Services.BillService;
import com.utn.TPFUDEE.Services.MeasurementService;
import com.utn.TPFUDEE.Utils.EntityURLBuilder;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private static final String ADDRESS_PATH = "address";

    @Autowired
    private AddressService addressService;
    private MeasurementService measurementService;
    private BillService billService;

    /*@GetMapping("/{id}")
    public ResponseEntity<Address> getById(@PathVariable Integer id) throws AddressNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).header("nombre", "Cuerpo").body( addressService.getById(id));
    }*/

    @PostMapping("/")
    public ResponseEntity add(@RequestBody Address address) throws AddressExistException {
        return ResponseEntity.status(HttpStatus.CREATED).location(EntityURLBuilder.buildURL(ADDRESS_PATH, addressService.add(address).getAddress_id())).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) throws AddressNotFoundException {
        addressService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).header("Aca", "Salio todo en orden man").build();
    }

    //Devolver mediciones //Innvestigar regex para fecha o como se escriba // PONER PAGINABLE
    @GetMapping("/{id}/measurement")
    public ResponseEntity<List<MeasurementProjection>> getAddressMeasurement(@PathVariable Integer id, @RequestParam String from, @RequestParam String to){
        return ResponseEntity.status(HttpStatus.OK).header("Todo ok").body(measurementService.getAllByDate(id, from, to));
    }

    //Devolver Facturas //Innvestigar regex fecha o como se escriba
    @GetMapping("/{id}/bill")
    public ResponseEntity<DevolcerProyeccion> getAddressBills(@PathVariable Integer id, @RequestParam String from, @RequestParam String to){
        return ResponseEntity;
    }

    //Devolver consumo //Innvestigar regex para fecha o como se escriba// Queda a hacer por complejidad de las tarifa "vieja"
    @GetMapping("/{id}/consume")
    public ResponseEntity<DevolcerProyeccion> getAddressMasurement(@PathVariable Integer id, @RequestParam String from, @RequestParam String to){
        return ResponseEntity;
    }


    @GetMapping("/{id}/billUnPaid")
    public ResponseEntity<List<BillProjection>> getAddressBillUnPaid(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).header("Todo en orden man").body(billService.getUnPaidBillsByAddress(id));
    }



}