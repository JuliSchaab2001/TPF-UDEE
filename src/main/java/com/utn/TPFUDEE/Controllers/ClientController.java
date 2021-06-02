package com.utn.TPFUDEE.Controllers;

import com.utn.TPFUDEE.Models.Client;
import com.utn.TPFUDEE.Models.Projections.BillProjection;
import com.utn.TPFUDEE.Models.Projections.ClientProjection;
import com.utn.TPFUDEE.Services.BillService;
import com.utn.TPFUDEE.Services.ClientService;
import com.utn.TPFUDEE.Utils.EntityURLBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private static final String CLIENT_PATH = "client";

    @Autowired
    private ClientService clientService;
    private BillService billService;

    @GetMapping("/{id}")
    public ResponseEntity<Client> getById(@PathVariable Integer id){

        return ResponseEntity.status(HttpStatus.OK).header("Nombre", "Cuerpo").body(clientService.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity add(@RequestBody Client client){
        return ResponseEntity.status(HttpStatus.CREATED).location(EntityURLBuilder.buildURL(CLIENT_PATH, clientService.add(client).getDni())).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id){
        clientService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).header("Aca", "Salio todo en orden man").build();
    }

    @GetMapping("/{id}/billUnPaid")
    public ResponseEntity<List<BillProjection>> getClientBillUnPaid(@PathVariable Integer id, @RequestParam Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).header("Todo en orden man").body(billService.getUnPaidBillsByClient(id, pageable).getContent());
    }

    @GetMapping("/topTen/")
    public ResponseEntity<List<ClientProjection>> getTopTenMostConsumers(){

        return ResponseEntity.status(HttpStatus.OK).header("Nice").body(clientService.getTopTenMostConsumers());
    }

}