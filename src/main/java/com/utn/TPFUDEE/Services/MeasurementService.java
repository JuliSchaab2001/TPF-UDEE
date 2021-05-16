package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Exceptions.Exist.MeasurementExistException;
import com.utn.TPFUDEE.Exceptions.NoContent.MeasurementNoContentException;
import com.utn.TPFUDEE.Exceptions.NotFound.MeasurementNotFoundException;
import com.utn.TPFUDEE.Models.Measurement;
import com.utn.TPFUDEE.Repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MeasurementService {

    private MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public Page<Measurement> getAll(Pageable pageable) throws MeasurementNoContentException {
        Page<Measurement> measurementList= measurementRepository.findAll(pageable);
        if(measurementList.isEmpty()){
            throw new MeasurementNoContentException();
        }

        return measurementList;
    }

    public void add(Measurement measurement) throws MeasurementExistException{
        boolean flag = false;
        for(Measurement var : this.measurementRepository.findAll()){
            if(var.getDateTime().equals(measurement.getDateTime()) && var.getMeter().getMeter_id().equals(measurement.getMeter().getMeter_id())){
                flag = true;
            }
        }
        if(flag){
            throw new MeasurementExistException();
        }else{
            measurementRepository.save(measurement);
        }
    }

    public Measurement getById(Integer id) throws MeasurementNotFoundException {
        return measurementRepository.findById(id).orElseThrow( () -> new MeasurementNotFoundException());
    }

    public void deleteById(Integer id) throws MeasurementNotFoundException {
        this.getById(id);
        measurementRepository.deleteById(id);
    }

}
