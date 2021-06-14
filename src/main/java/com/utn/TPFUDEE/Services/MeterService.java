package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Models.Meter;
import com.utn.TPFUDEE.Repositories.MeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MeterService {

    private MeterRepository meterRepository;

    @Autowired
    public MeterService(MeterRepository meterRepository) {
        this.meterRepository = meterRepository;
    }

    public Page<Meter> getAll(Pageable pageable){
        Page<Meter> meterList = meterRepository.findAll(pageable);

        if(meterList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Empty Meter List");
        }
        return meterList;
    }

    public Meter add(Meter meter){
        if(meterRepository.findBySerialNumber(meter.getSerialNumber())!=null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Meter Already Exist");
        }else{
            return meterRepository.save(meter);
        }
    }

    public Meter getById(Integer id){
        return meterRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Meter Not Found"));
    }

    public void deleteById(Integer id){
        this.getById(id);
        meterRepository.deleteById(id);
    }

    public Meter getBySerialNumber(String serialNumber){
        return meterRepository.findBySerialNumber(serialNumber);
    }

    public Meter update(Meter meter) {
        if(this.getById(meter.getMeterId()) != null)
            return meterRepository.save(meter);
        else
            return null;
    }
}
