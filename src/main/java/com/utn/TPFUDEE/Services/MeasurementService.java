package com.utn.TPFUDEE.Services;

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

import java.time.LocalDate;



@Service
public class MeasurementService {


    private MeasurementRepository measurementRepository;
    private AddressService addressService;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, AddressService addressService) {
        this.measurementRepository = measurementRepository;
        this.addressService = addressService;
    }

    public Page<Measurement> getAll(Pageable pageable) {
        Page<Measurement> measurementList= measurementRepository.findAll(pageable);
        if(measurementList.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Empty Measurement List");
        }

        return measurementList;
    }

    public Measurement add(Measurement measurement){
        return measurementRepository.save(measurement);
    }

    public Page<MeasurementProjection> getAllByDate(Integer id, String from, String to, Pageable pageable){
        Meter meter = addressService.getById(id).getMeter();
        Page<MeasurementProjection> projectionList;
        if(meter != null)
            projectionList = measurementRepository.findByMeterIdAndDateBetween(meter.getMeterId(), from, to, pageable);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "invalid Address");
        if(!projectionList.isEmpty()){
            return projectionList;
        }else{
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No measurement between this dates");
        }

    }

    public Measurement getById(Integer id){
        return measurementRepository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Measurement Not Found"));
    }

    //Deberia llamar al repositorio, no a this.getById()
    public Integer deleteById(Integer id){
        this.getById(id);
        measurementRepository.deleteById(id);
        return id;
    }

    public MoneyAndKwProjection getAddressConsumes(Integer id, LocalDate from, LocalDate to){
        Meter meter = addressService.getById(id).getMeter();
        if(meter!=null)
            return measurementRepository.getAddressConsumes(id,from.atTime(00,00,0),to.atTime(00,00,00));
        else
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Address Not Found");
    }

}
