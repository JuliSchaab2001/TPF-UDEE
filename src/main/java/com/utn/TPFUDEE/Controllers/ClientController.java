package com.utn.TPFUDEE.Controllers;

import com.utn.TPFUDEE.Models.Client;
import com.utn.TPFUDEE.Models.DTO.UserDTO;
import com.utn.TPFUDEE.Models.Projections.BillProjection;
import com.utn.TPFUDEE.Models.Projections.ClientProjection;
import com.utn.TPFUDEE.Models.User;
import com.utn.TPFUDEE.Services.BillService;
import com.utn.TPFUDEE.Services.ClientService;
import com.utn.TPFUDEE.Services.UserService;
import com.utn.TPFUDEE.Utils.EntityURLBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private static final String CLIENT_PATH = "client";

    private ClientService clientService;
    private BillService billService;
    private UserService userService;

    @Autowired
    public ClientController(ClientService clientService, BillService billService, UserService userService) {
        this.clientService = clientService;
        this.billService = billService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getById(Authentication authentication,@PathVariable Integer id){
        if(!this.validate(id, authentication))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "UNAUTHORIZED USER");
        return ResponseEntity.status(HttpStatus.OK).header("Nombre", "Cuerpo").body(clientService.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity add(@RequestBody Client client){
        return ResponseEntity.status(HttpStatus.CREATED).location(EntityURLBuilder.buildURL(CLIENT_PATH, clientService.add(client).getDni())).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(Authentication authentication, @PathVariable Integer id){
        if(!this.validateRol(authentication))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "UNAUTHORIZED USER");
        clientService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).header("Aca", "Salio todo en orden man").build();
    }

    @GetMapping("/{id}/billUnPaid")
    public ResponseEntity<List<BillProjection>> getClientBillUnPaid(Authentication authentication, @PathVariable Integer id, @RequestParam Pageable pageable){
        if(!this.validate(id, authentication))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "UNAUTHORIZED USER");
        return ResponseEntity.status(HttpStatus.OK).header("Todo en orden man").body(billService.getUnPaidBillsByClient(id, pageable).getContent());
    }

    @GetMapping("/topTen/")
    public ResponseEntity<List<ClientProjection>> getTopTenMostConsumers(Authentication authentication){
        if(!this.validateRol(authentication))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "UNAUTHORIZED USER");
        return ResponseEntity.status(HttpStatus.OK).header("Nice").body(clientService.getTopTenMostConsumers());
    }

    private boolean validate(Integer dni, Authentication authentication){
        User user = userService.getById(((UserDTO)authentication.getPrincipal()).getUserId());
        if(user.isEmployee())
            return true;
        else
            return user.getClient().getDni().equals(dni);
    }

    private boolean validateRol(Authentication authentication){
        User user = userService.getById(((UserDTO)authentication.getPrincipal()).getUserId());
        return user.isEmployee();
    }


}


