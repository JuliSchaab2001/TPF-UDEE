package com.utn.TPFUDEE.Controllers;


import com.utn.TPFUDEE.Services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private BillService billService;

    @GetMapping("/")
    public List<Bill > getAll(){
        return billService.getAll();
    }

    @GetMapping("/{id}")
    public Bill getById(@PathVariable Integer id){
        return billService.getById(id);
    }

    @PostMapping("/")
    public void add(@RequestBody Bill bill){
        billService.add(bill);
    }
}