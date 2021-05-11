package com.utn.TPFUDEE.Controllers;

import com.utn.TPFUDEE.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/")
    public List<Client> getAll(){
        return clientService.getAll();
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable Integer id){
        return clientService.getById(id);
    }

    @PostMapping("/")
    public void add(@RequestBody Client client){
        clientService.add(client);
    }
}