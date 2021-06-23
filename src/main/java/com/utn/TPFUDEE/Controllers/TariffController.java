package com.utn.TPFUDEE.Controllers;

import com.utn.TPFUDEE.Models.DTO.UserDTO;
import com.utn.TPFUDEE.Models.Tariff;
import com.utn.TPFUDEE.Models.User;
import com.utn.TPFUDEE.Services.TariffService;
import com.utn.TPFUDEE.Services.UserService;
import com.utn.TPFUDEE.Utils.EntityURLBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/tariff")
public class TariffController {

    public static final String TARIFF_PATH = "tariff";

    private TariffService tariffService;
    private UserService userService;

    @Autowired
    public TariffController(TariffService tariffService, UserService userService) {
        this.tariffService = tariffService;
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity add(Authentication authentication, @RequestBody Tariff tariff){
        if(!this.validateRol(authentication))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "UNAUTHORIZED USER");
        return ResponseEntity.status(HttpStatus.CREATED).location(EntityURLBuilder.buildURL(TARIFF_PATH,tariffService.add(tariff).getTariffId())).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(Authentication authentication, @PathVariable Integer id){
        if(!this.validateRol(authentication))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "UNAUTHORIZED USER");
        tariffService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).header("Aca", "Salio todo en orden man").build();
    }

    @PutMapping("/")
    public ResponseEntity update(Authentication authentication, @RequestBody Tariff tariff){
        if(!this.validateRol(authentication))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "UNAUTHORIZED USER");
        return ResponseEntity.status(HttpStatus.OK).location(EntityURLBuilder.buildURL(TARIFF_PATH,tariffService.update(tariff).getTariffId())).build();
    }

    private boolean validateRol(Authentication authentication){
        User user = userService.getById(((UserDTO)authentication.getPrincipal()).getUserId());
        return user.isEmployee();
    }
}
