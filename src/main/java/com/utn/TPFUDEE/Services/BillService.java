package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Controllers.BillController;
import com.utn.TPFUDEE.Repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class BillService {

    private BillRepository billRepository;


    @Autowired
    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public List<Bill> getAll(){
        return billRepository.findAll();
    }

    public void add(Bill bill){
        billRepository.save(bill);
    }

    public Bill getById(Integer id){
        return billRepository.findById(id).orElseThrow( () -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }
}
