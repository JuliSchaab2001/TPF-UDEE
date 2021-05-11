package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class MeasurementService {

    private MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public List<Measurement> getAll(){
        return measurementRepository.findAll();
    }

    public void save(Measurement measurement){
        measurementRepository.save(measurement);
    }

    public Measurement getById(Integer id){
        return measurementRepository.findById(id).orElseThrow( () -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }
}
