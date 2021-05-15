package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Exceptions.*;
import com.utn.TPFUDEE.Models.Meter;
import com.utn.TPFUDEE.Models.MeterType;
import com.utn.TPFUDEE.Repositories.MeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;


import java.util.List;

@Service
public class MeterService {

    private MeterRepository meterRepository;

    @Autowired
    public MeterService(MeterRepository meterRepository) {
        this.meterRepository = meterRepository;
    }

    public List<Meter> getAll() throws MeterNoContentException {
        List<Meter> meterList = meterRepository.findAll();

        if(meterList.isEmpty()) {
            System.out.println(meterList);
            throw new MeterNoContentException();
        }
        return meterList;
    }

    public void add(Meter meter) throws MeterExistException {
        boolean flag = false;
        for(Meter var : this.meterRepository.findAll()){
            if(var.getSerialNumber().equals(meter.getSerialNumber())){
                flag = true;
            }
        }
        if(flag){
            throw new MeterExistException();
        }else{
            meterRepository.save(meter);
        }
    }

    public Meter getById(Integer id) throws MeterNotFoundException {
        return meterRepository.findById(id).orElseThrow(()-> new MeterNotFoundException());
    }
}
