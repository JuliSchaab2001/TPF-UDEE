package com.utn.TPFUDEE.Controllers;

import com.utn.TPFUDEE.DTO.ClientDTO;
import com.utn.TPFUDEE.Exceptions.Exist.ClientExistException;
import com.utn.TPFUDEE.Exceptions.NoContent.ClientNoContentException;
import com.utn.TPFUDEE.Exceptions.NotFound.ClientNotFoundException;
import com.utn.TPFUDEE.Exceptions.NotFound.UserNotFoundException;
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
    public ResponseEntity<List<ClientDTO>> getAll() throws ClientNoContentException {
        return ResponseEntity.status(HttpStatus.OK).header("Nombre", "Cuerpo").body(ClientDTO.mapClientListToDTO(clientService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getById(@PathVariable Integer id) throws ClientNotFoundException {

        return ResponseEntity.status(HttpStatus.OK).header("Nombre", "Cuerpo").body(ClientDTO.mapClientToDTO(clientService.getById(id)));
    }

    @PostMapping("/")
    public ResponseEntity add(@RequestBody Client client) throws ClientExistException {
        clientService.add(client);
        return ResponseEntity.status(HttpStatus.ACCEPTED).header("Cuerpo", "Aca habria que poner un header location").build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) throws ClientNotFoundException {
        clientService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).header("Aca", "Salio todo en orden man").build();
    }
}