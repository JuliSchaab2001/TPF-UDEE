package com.utn.TPFUDEE.Controllers;

import com.utn.TPFUDEE.Models.Address;
import com.utn.TPFUDEE.Models.DTO.UserDTO;
import com.utn.TPFUDEE.Models.Projections.BillProjection;
import com.utn.TPFUDEE.Models.Projections.MeasurementProjection;
import com.utn.TPFUDEE.Models.Projections.MoneyAndKwProjection;
import com.utn.TPFUDEE.Models.Projections.AddressProjection;
import com.utn.TPFUDEE.Models.User;
import com.utn.TPFUDEE.Services.AddressService;
import com.utn.TPFUDEE.Services.BillService;
import com.utn.TPFUDEE.Services.MeasurementService;
import com.utn.TPFUDEE.Services.UserService;
import com.utn.TPFUDEE.Utils.EntityURLBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private static final String ADDRESS_PATH = "address";

    private AddressService addressService;
    private MeasurementService measurementService;
    private BillService billService;
    private UserService userService;

    @Autowired
    public AddressController(AddressService addressService, MeasurementService measurementService, BillService billService, UserService userService) {
        this.addressService = addressService;
        this.measurementService = measurementService;
        this.billService = billService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressProjection> getById(Authentication authentication , @PathVariable Integer id){
        if(!this.validate(id, authentication))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "UNAUTHORIZED USER");
        return ResponseEntity.status(HttpStatus.OK).header("nombre", "Cuerpo").body( addressService.getOnlyAddressById(id));
    }

    @PostMapping("/")
    public ResponseEntity add(Authentication authentication ,@RequestBody Address address){
        if(!this.validateRol(authentication))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "UNAUTHORIZED USER");
        return ResponseEntity.status(HttpStatus.CREATED).location(EntityURLBuilder.buildURL(ADDRESS_PATH, addressService.add(address).getAddressId())).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(Authentication authentication ,@PathVariable Integer id){
        if(!this.validateRol(authentication))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "UNAUTHORIZED USER");
        addressService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).header("Aca", "Salio todo en orden man").build();
    }

    @PutMapping("/")
    public ResponseEntity update(Authentication authentication ,@RequestBody Address address){
        if(!this.validateRol(authentication))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "UNAUTHORIZED USER");
        return ResponseEntity.status(HttpStatus.OK).location(EntityURLBuilder.buildURL(ADDRESS_PATH,addressService.update(address).getAddressId())).build();
    }

    @GetMapping("/{id}/measurement")
    public ResponseEntity<List<MeasurementProjection>> getAddressMeasurement(Authentication authentication ,
                                                                             @PathVariable Integer id,
                                                                             @RequestParam String from,
                                                                             @RequestParam String to,
                                                                             @RequestParam Pageable pageable){
        if(!this.validate(id, authentication))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "UNAUTHORIZED USER");
        return ResponseEntity.status(HttpStatus.OK).header("Todo ok").body(measurementService.getAllByDate(id, from, to, pageable).getContent());


    }

    @GetMapping("/{id}/bill")
    public ResponseEntity<List<BillProjection>> getBillsByDates(Authentication authentication ,
                                                                @PathVariable Integer id,
                                                                @RequestParam @DateTimeFormat(pattern = "yyyyy-MM-DD") String from,
                                                                @RequestParam @DateTimeFormat(pattern = "yyyyy-MM-DD") String to,
                                                                @RequestParam Pageable pageable) {
        if(!this.validate(id, authentication))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "UNAUTHORIZED USER");
        return ResponseEntity.status(HttpStatus.OK).header("Tuty ok").body(billService.getBillsByDates(id, from, to, pageable).getContent());
    }

    @GetMapping("/{id}/consume")
    public ResponseEntity<MoneyAndKwProjection> getAddressConsumes(Authentication authentication ,
                                                                   @PathVariable Integer id,
                                                                   @RequestParam @DateTimeFormat(pattern = "yyyyy-MM-DD") String from,
                                                                   @RequestParam @DateTimeFormat(pattern = "yyyyy-MM-DD") String to){
        if(!this.validate(id, authentication))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "UNAUTHORIZED USER");
        return ResponseEntity.status(HttpStatus.OK).header("Poner header").body(measurementService.getAddressConsumes(id, from, to));
    }

    @GetMapping("/{id}/billUnPaid")
    public ResponseEntity<List<BillProjection>> getAddressBillUnPaid(Authentication authentication ,@PathVariable Integer id, @RequestParam Pageable pageable){
        if(!this.validate(id, authentication))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "UNAUTHORIZED USER");
        return ResponseEntity.status(HttpStatus.OK).header("Todo en orden man").body(billService.getUnPaidBillsByAddress(id, pageable).getContent());
    }

    private boolean validate(Integer addressId, Authentication authentication){
        User user = userService.getById(((UserDTO)authentication.getPrincipal()).getUserId());
        Address address= addressService.getById(addressId);
        Integer id = address.getClient().getUser().getUserId();
        if(user.isEmployee())
            return true;
        else
            return user.getUserId().equals(id);
    }

    private boolean validateRol(Authentication authentication){
        User user = userService.getById(((UserDTO)authentication.getPrincipal()).getUserId());
        return user.isEmployee();
    }




}