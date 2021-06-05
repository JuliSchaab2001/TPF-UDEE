package com.utn.TPFUDEE.Controllers;

import com.utn.TPFUDEE.Models.DTO.UserDTO;
import com.utn.TPFUDEE.Models.Meter;
import com.utn.TPFUDEE.Models.Tariff;
import com.utn.TPFUDEE.Models.User;
import com.utn.TPFUDEE.Services.MeterService;
import com.utn.TPFUDEE.Services.UserService;
import com.utn.TPFUDEE.Utils.EntityURLBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.Authenticator;

@RestController
@RequestMapping("/meter")
public class MeterController {

    private static final String METER_PATH = "meter";

    @Autowired
    private MeterService meterService;
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity add(Authentication authentication, @RequestBody Meter meter){
        if(!this.validateRol(authentication))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "UNAUTHORIZED USER");
        return ResponseEntity.status(HttpStatus.CREATED).location(EntityURLBuilder.buildURL(METER_PATH,meterService.add(meter).getMeterId())).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(Authentication authentication,@PathVariable Integer id){
        if(!this.validateRol(authentication))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "UNAUTHORIZED USER");
        meterService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).header("Aca", "Salio todo en orden man").build();
    }

    @PutMapping("/")
    public ResponseEntity update(Authentication authentication,@RequestBody Meter meter){
        if(!this.validateRol(authentication))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "UNAUTHORIZED USER");
        if(meterService.getById(meter.getMeterId()) != null)
            return ResponseEntity.status(HttpStatus.OK).location(EntityURLBuilder.buildURL(METER_PATH,meterService.update(meter).getMeterId())).build();
        return null;
    }

    private boolean validateRol(Authentication authentication){
        User user = userService.getById(((UserDTO)authentication.getPrincipal()).getUserId());
        return user.isEmployee();
    }
}