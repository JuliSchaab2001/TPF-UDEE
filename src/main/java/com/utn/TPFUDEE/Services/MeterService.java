package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Repositories.MeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import sun.net.www.http.HttpClient;

@Service
public class MeterService {

    private MeterRepository meterRepository;

    @Autowired
    public MeterService(MeterRepository meterRepository) {
        this.meterRepository = meterRepository;
    }

    public List<Meter> getAll(){
        return meterRepository.findAll();
    }

    public void add(Meter meter){
        meterRepository.save(meter);
    }

    public Meter getById(Integer id){
        return meterRepository.findById(id).orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }
}
