package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Controllers.BillController;
import com.utn.TPFUDEE.Exceptions.Exist.BillExistException;
import com.utn.TPFUDEE.Exceptions.Exist.ClientExistException;
import com.utn.TPFUDEE.Exceptions.NoContent.BillNoContentException;
import com.utn.TPFUDEE.Exceptions.NotFound.BillNotFoundException;
import com.utn.TPFUDEE.Models.Bill;
import com.utn.TPFUDEE.Models.Client;
import com.utn.TPFUDEE.Repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class BillService {

    private BillRepository billRepository;


    @Autowired
    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public Page<Bill> getAll(Pageable pageable) throws BillNoContentException{
        Page<Bill> billList= billRepository.findAll(pageable);

        if(billList.isEmpty()){
            throw new BillNoContentException();
        }
        return billList;
    }

    public void add(Bill bill) throws BillExistException{
        boolean flag = false;
        for(Bill var : this.billRepository.findAll()){
            if(var.getInitialDate().equals(bill.getInitialDate()) && var.getFinalDate().equals(bill.getFinalDate()) && var.getMeter().getMeter_id().equals(bill.getMeter().getMeter_id())){
                flag = true;
            }
        }
        if(flag){
            throw new BillExistException();
        }else{
            billRepository.save(bill);
        }
    }

    public Bill getById(Integer id) throws BillNotFoundException{
        return billRepository.findById(id).orElseThrow( () -> new BillNotFoundException());
    }

    public void deleteById(Integer id) throws BillNotFoundException{
        this.getById(id);
        billRepository.deleteById(id);
    }
}
