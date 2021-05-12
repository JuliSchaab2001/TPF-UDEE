package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Repositories.MeterTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class MeterTypeService {

    private MeterTypeRepository meterTypeRepository;

    @Autowired
    public MeterTypeService(MeterTypeRepository meterTypeRepository) {
        this.meterTypeRepository = meterTypeRepository;
    }

    public List<MeterType> getAll(){
        return meterTypeRepository.findAll();
    }

    public void add(MeterType meterType){
        meterTypeRepository.save(meterType);
    }

    public MeterType getById(Integer id){
        return meterTypeRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }
}
