package com.utn.TPFUDEE.Controllers;


import com.utn.TPFUDEE.Models.Tariff;
import com.utn.TPFUDEE.Services.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tariff")
public class TariffController {
    @Autowired
    private TariffService tariffService;

    @GetMapping("/")
    public List<Tariff> getAll(){
        return tariffService.getAll();
    }

    @GetMapping("/{id}")
    public Tariff getById(@PathVariable Integer id){
        return tariffService.getById(id);
    }

    @PostMapping("/")
    public void add(@RequestBody Tariff tariff){
        tariffService.add(tariff);
    }
}
