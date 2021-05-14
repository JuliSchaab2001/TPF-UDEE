package com.utn.TPFUDEE.Controllers;

import com.utn.TPFUDEE.Exceptions.ClientExistException;
import com.utn.TPFUDEE.Models.Client;
import com.utn.TPFUDEE.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/")
    public ResponseEntity<List<Client>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).header("Nombre", "Cuerpo").body(clientService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getById(@PathVariable Integer id){

        return ResponseEntity.status(HttpStatus.OK).header("Nombre", "Cuerpo").body(clientService.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity add(@RequestBody Client client) throws ClientExistException {
        clientService.add(client);
        return ResponseEntity.status(HttpStatus.ACCEPTED).header("Cuerpo", "Aca habria que poner un header location").build();
    }
}