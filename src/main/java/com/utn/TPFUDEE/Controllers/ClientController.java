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
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    public static final String CLIENT_PATH = "client";

    private ClientService clientService;
    private BillService billService;
    private UserService userService;

    @Autowired
    public ClientController(ClientService clientService, BillService billService, UserService userService) {
        this.clientService = clientService;
        this.billService = billService;
        this.userService = userService;
    }

    // Deberiamos hacer las validaciones como en el UserController

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
        return ResponseEntity.status(HttpStatus.OK).header("Aca", "Salio todo en orden").build();
    }

    @GetMapping("/{id}/billUnPaid")
    public ResponseEntity<List<BillProjection>> getClientBillUnPaid(Authentication authentication, @PathVariable Integer id,
                                                                    @RequestParam(defaultValue = "0") Integer page,
                                                                    @RequestParam(defaultValue = "10") Integer size){
        if(!this.validate(id, authentication))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "UNAUTHORIZED USER");
        return ResponseEntity.status(HttpStatus.OK).header("Todo en orden").body(billService.getUnPaidBillsByClient(id, PageRequest.of(page, size)).getContent());
    }

    @GetMapping("/topTen/")
    public ResponseEntity<List<ClientProjection>> getTopTenMostConsumers(Authentication authentication,
                                                                         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-DD") String from,
                                                                         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-DD") String to){
        if(!this.validateRol(authentication))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "UNAUTHORIZED USER");
        return ResponseEntity.status(HttpStatus.OK).header("Nice").body(clientService.getTopTenMostConsumers((LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"))), (LocalDate.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd")))));
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


