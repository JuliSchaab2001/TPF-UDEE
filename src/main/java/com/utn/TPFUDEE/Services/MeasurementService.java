package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Exceptions.Exist.MeasurementExistException;
import com.utn.TPFUDEE.Exceptions.NoContent.MeasurementNoContentException;
import com.utn.TPFUDEE.Exceptions.NotFound.AddressNotFoundException;
import com.utn.TPFUDEE.Exceptions.NotFound.MeasurementNotFoundException;
import com.utn.TPFUDEE.Models.Measurement;
import com.utn.TPFUDEE.Models.Meter;
import com.utn.TPFUDEE.Models.Projections.MeasurementProjection;
import com.utn.TPFUDEE.Models.Projections.MoneyAndKwProjection;
import com.utn.TPFUDEE.Repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class MeasurementService {

    private MeasurementRepository measurementRepository;
    private AddressService addressService;

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

    public Measurement add(Measurement measurement) throws MeasurementExistException{
        boolean flag = false;
        for(Measurement var : this.measurementRepository.findAll()){
            if(var.getDate().equals(measurement.getDate()) && var.getMeter().getMeterId().equals(measurement.getMeter().getMeterId())){
                flag = true;
            }
        }
        if(flag){
            throw new MeasurementExistException();
        }else{
           return measurementRepository.save(measurement);
        }
    }

    public Page<MeasurementProjection> getAllByDate(Integer id, String from, String to, Pageable pageable) throws AddressNotFoundException {
        Meter meter = addressService.getById(id).getMeter();
        Page<MeasurementProjection> projectionList= null;
        if(meter != null)
            projectionList = measurementRepository.findByMeterAndDateBetween(meter.getMeterId(), from, to, pageable);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "invalid Address");
        if(!projectionList.isEmpty()){
            return projectionList;
        }else{
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No measurement between this dates");
        }

    }

    public Measurement getById(Integer id) throws MeasurementNotFoundException {
        return measurementRepository.findById(id).orElseThrow( () -> new MeasurementNotFoundException());
    }

    public void deleteById(Integer id) throws MeasurementNotFoundException {
        this.getById(id);
        measurementRepository.deleteById(id);
    }

    public MoneyAndKwProjection getAddressConsumes(Integer id, String from, String to) throws AddressNotFoundException {
        Meter meter = addressService.getById(id).getMeter();
        if(meter!=null)
            return measurementRepository.getAddressConsumes(id,from,to);
        else
            throw  new AddressNotFoundException();
    }
}
