package com.utn.TPFUDEE.Controllers;

import com.utn.TPFUDEE.Models.Address;
import com.utn.TPFUDEE.Models.Meter;
import com.utn.TPFUDEE.Models.Projections.BillProjection;
import com.utn.TPFUDEE.Models.Projections.MeasurementProjection;
import com.utn.TPFUDEE.Models.Projections.MoneyAndKwProjection;
import com.utn.TPFUDEE.Models.Projections.addressProjection;
import com.utn.TPFUDEE.Services.AddressService;
import com.utn.TPFUDEE.Services.BillService;
import com.utn.TPFUDEE.Services.MeasurementService;
import com.utn.TPFUDEE.Utils.EntityURLBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
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

    @GetMapping("/{id}")
    public ResponseEntity<addressProjection> getById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).header("nombre", "Cuerpo").body( addressService.getOnlyAddressById(id));
    }

    @PostMapping("/")
    public ResponseEntity add(@RequestBody Address address){
        System.out.println(address.toString());
        return ResponseEntity.status(HttpStatus.CREATED).location(EntityURLBuilder.buildURL(ADDRESS_PATH, addressService.add(address).getAddressId())).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id){
        addressService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).header("Aca", "Salio todo en orden man").build();
    }

    @PutMapping("/")
    public ResponseEntity update(@RequestBody Address address){
        if(addressService.getById(address.getAddressId()) != null)
            return ResponseEntity.status(HttpStatus.OK).location(EntityURLBuilder.buildURL(ADDRESS_PATH,addressService.update(address).getAddressId())).build();
        else
            return this.add(address);
    }

    @GetMapping("/{id}/measurement")
    public ResponseEntity<List<MeasurementProjection>> getAddressMeasurement(@PathVariable Integer id,
                                                                             @RequestParam String from,
                                                                             @RequestParam String to,
                                                                             @RequestParam Pageable pageable){

        return ResponseEntity.status(HttpStatus.OK).header("Todo ok").body(measurementService.getAllByDate(id, from, to, pageable).getContent());


    }

    @GetMapping("/{id}/bill")
    public ResponseEntity<List<BillProjection>> getBillsByDates(@PathVariable Integer id,
                                                                @RequestParam @DateTimeFormat(pattern = "yyyyy-MM-DD") String from,
                                                                @RequestParam @DateTimeFormat(pattern = "yyyyy-MM-DD") String to,
                                                                @RequestParam Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).header("Tuty ok").body(billService.getBillsByDates(id, from, to, pageable).getContent());
    }

    @GetMapping("/{id}/consume")
    public ResponseEntity<MoneyAndKwProjection> getAddressConsumes(@PathVariable Integer id,
                                                                   @RequestParam @DateTimeFormat(pattern = "yyyyy-MM-DD") String from,
                                                                   @RequestParam @DateTimeFormat(pattern = "yyyyy-MM-DD") String to){
        return ResponseEntity.status(HttpStatus.OK).header("Poner header").body(measurementService.getAddressConsumes(id, from, to));
    }

    @GetMapping("/{id}/billUnPaid")
    public ResponseEntity<List<BillProjection>> getAddressBillUnPaid(@PathVariable Integer id, @RequestParam Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).header("Todo en orden man").body(billService.getUnPaidBillsByAddress(id, pageable).getContent());
    }



}