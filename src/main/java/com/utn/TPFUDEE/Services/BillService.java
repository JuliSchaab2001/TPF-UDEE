package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Controllers.BillController;
import com.utn.TPFUDEE.Exceptions.Exist.BillExistException;
import com.utn.TPFUDEE.Exceptions.Exist.ClientExistException;
import com.utn.TPFUDEE.Exceptions.NoContent.BillNoContentException;
import com.utn.TPFUDEE.Exceptions.NotFound.AddressNotFoundException;
import com.utn.TPFUDEE.Exceptions.NotFound.BillNotFoundException;
import com.utn.TPFUDEE.Models.Bill;
import com.utn.TPFUDEE.Models.Client;
import com.utn.TPFUDEE.Models.Meter;
import com.utn.TPFUDEE.Models.Projections.BillProjection;
import com.utn.TPFUDEE.Repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BillService {

    private BillRepository billRepository;
    private AddressService addressService;


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

    public Bill add(Bill bill) throws BillExistException{
        boolean flag = false;
        for(Bill var : this.billRepository.findAll()){
            if(var.getInitialDate().equals(bill.getInitialDate()) && var.getFinalDate().equals(bill.getFinalDate()) && var.getMeter().getMeter_id().equals(bill.getMeter().getMeter_id())){
                flag = true;
            }
        }
        if(flag){
            throw new BillExistException();
        }else{
            return billRepository.save(bill);
        }
    }

    public Bill getById(Integer id) throws BillNotFoundException{
        return billRepository.findById(id).orElseThrow( () -> new BillNotFoundException());
    }

    public void deleteById(Integer id) throws BillNotFoundException{
        this.getById(id);
        billRepository.deleteById(id);
    }

    public Page<BillProjection> getUnPaidBillsByAddress(Integer id, Pageable pageable) throws AddressNotFoundException {
        Meter meter =  addressService.getById(id).getMeter();
        Page<BillProjection> billProjections = null;
        if(meter != null)
            billProjections = billRepository.getBillByMeter_idAndIsPaid(meter.getMeter_id(),0,pageable);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "invalid Address");
        if(!billProjections.isEmpty())
            return billProjections;
        else
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay facturas Inpagas");

    }

    public List<BillProjection> getUnPaidBillsByClient(Integer id){
        return billRepository.getUnPaidBillsByClient(id);
    }

    public Page<BillProjection> getBillsByDates(Integer id, String from, String to, Pageable pageable){
        return billRepository.getBillByBill_idAndFinalDateBetween(id, from, to, pageable);
    }
}
