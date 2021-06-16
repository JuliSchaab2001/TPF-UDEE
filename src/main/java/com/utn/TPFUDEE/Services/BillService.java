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
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillService {

    private BillRepository billRepository;
    private AddressService addressService;
    private ClientService clientService;
    private MeterService meterService;


    @Autowired
    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
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

    public Integer deleteById(Integer id){
        this.getById(id);
        billRepository.deleteById(id);
        return id;
    }

    public Page<BillProjection> getUnPaidBillsByAddress(Integer id, Pageable pageable) {
        Meter meter =  addressService.getById(id).getMeter();
        Page<BillProjection> billProjections = null;
        if(meter != null)
            billProjections = billRepository.getBillByMeterAndIsPaid(meter.getMeterId(),0,pageable);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "invalid Address");
        if(!billProjections.isEmpty())
            return billProjections;
        else
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay facturas Inpagas");

    }

    public Page<BillProjection> getUnPaidBillsByClient(Integer id, Pageable pageable) {
        Client client = clientService.getById(id);
        List<Integer> meterIds = new ArrayList<>();
        if(client == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client Not Found");
        }else{
            for(Address address: client.getAddressList()){
                meterIds.add(address.getMeter().getMeterId());
            }
            return billRepository.getBillByMeterInAndIsPaid(meterIds,0, pageable);
        }

    }

    public Page<BillProjection> getBillsByDates(Integer id, String from, String to, Pageable pageable){
        return billRepository.getBillByBillIdAndFinalDateBetween(id, from, to, pageable);
    }
}
