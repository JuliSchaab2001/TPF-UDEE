package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Models.Address;
import com.utn.TPFUDEE.Models.Bill;
import com.utn.TPFUDEE.Models.Client;
import com.utn.TPFUDEE.Models.Meter;
import com.utn.TPFUDEE.Models.Projections.BillProjection;
import com.utn.TPFUDEE.Repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
public class BillService {

    private BillRepository billRepository;
    private AddressService addressService;
    private ClientService clientService;
    private MeterService meterService;


    @Autowired
    public BillService(BillRepository billRepository, AddressService addressService, ClientService clientService, MeterService meterService) {
        this.billRepository = billRepository;
        this.addressService = addressService;
        this.clientService = clientService;
        this.meterService = meterService;
    }



    public Page<Bill> getAll(Pageable pageable){
        Page<Bill> billList= billRepository.findAll(pageable);

        if(billList.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Empty Bill List");
        }
        return billList;
    }

    public Bill getById(Integer id) {
        return billRepository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bill Not Found"));
    }

    //Deberia llamar al repositorio, no a this.getById()
    public Integer deleteById(Integer id){
        this.getById(id);
        billRepository.deleteById(id);
        return id;
    }

    public Page<BillProjection> getUnPaidBillsByAddress(Integer id, Pageable pageable) {
        Meter meter =  addressService.getById(id).getMeter();
        Page<BillProjection> billProjections = null;
        if(meter != null)
            billProjections = billRepository.getBillByMeterAndIsPaid(meter.getMeterId(),false,pageable);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "invalid Address");
        if(!billProjections.isEmpty())
            return billProjections;
        else
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay facturas Impagas");

    }

    public Page<BillProjection> getUnPaidBillsByClient(Integer id, Pageable pageable) {
        Client client = clientService.getById(id);
        if(client == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client Not Found");
        }else{
            return billRepository.getBillByMeterInAndIsPaid(id,false, pageable);
        }
    }

    public Page<BillProjection> getBillsByDates(Integer id, LocalDate from, LocalDate to, Pageable pageable){
        return billRepository.getBillByBillIdAndFinalDateBetween(id, (from.atTime(00,00,00)).toString(),(to.atTime(00,00,00)).toString(), pageable);
    }

    /*@Scheduled(cron="0 0 0 1 1/1 *", zone = "America/Argentina")
    private void bill_all(){
        billRepository.bill_all();
    }*/
}
