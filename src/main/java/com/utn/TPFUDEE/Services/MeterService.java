package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Exceptions.Exist.MeterExistException;
import com.utn.TPFUDEE.Exceptions.NoContent.MeterNoContentException;
import com.utn.TPFUDEE.Exceptions.NotFound.MeterNotFoundException;
import com.utn.TPFUDEE.Models.Meter;
import com.utn.TPFUDEE.Repositories.MeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MeterService {

    private MeterRepository meterRepository;

    @Autowired
    public MeterService(MeterRepository meterRepository) {
        this.meterRepository = meterRepository;
    }

    public Page<Meter> getAll(Pageable pageable) throws MeterNoContentException {
        Page<Meter> meterList = meterRepository.findAll(pageable);

        if(meterList.isEmpty()) {
            throw new MeterNoContentException();
        }
        return meterList;
    }

    public Meter add(Meter meter) throws MeterExistException {
        boolean flag = false;
        for(Meter var : this.meterRepository.findAll()){
            if(var.getSerialNumber().equals(meter.getSerialNumber())){
                flag = true;
            }
        }
        if(flag){
            throw new MeterExistException();
        }else{
            return meterRepository.save(meter);
        }
    }

    public Meter getById(Integer id) throws MeterNotFoundException {
        return meterRepository.findById(id).orElseThrow(()-> new MeterNotFoundException());
    }

    public void deleteById(Integer id) throws MeterNotFoundException {
        this.getById(id);
        meterRepository.deleteById(id);
    }
}
