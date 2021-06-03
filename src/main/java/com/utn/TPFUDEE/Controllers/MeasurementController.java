package com.utn.TPFUDEE.Controllers;

import com.utn.TPFUDEE.Models.DTO.MeasurementDTO;
import com.utn.TPFUDEE.Models.Measurement;
import com.utn.TPFUDEE.Models.Meter;
import com.utn.TPFUDEE.Services.MeasurementService;
import com.utn.TPFUDEE.Services.MeterService;
import com.utn.TPFUDEE.Utils.EntityURLBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    private static final String MEASUREMENT_PATH = "measurement";

    @Autowired
    private MeasurementService measurementService;
    private MeterService meterService;

    public MeasurementController(MeasurementService measurementService, MeterService meterService) {
        this.measurementService = measurementService;
        this.meterService = meterService;
    }

    @PostMapping("/")
    public ResponseEntity add(@RequestBody MeasurementDTO measurement) {
        System.out.println(measurement.toString());
        Meter meter= meterService.getBySerialNumber(measurement.getSerialNumber());

        if(meter != null && meter.getPassword().equals(measurement.getPassword())) {
            return ResponseEntity.status(HttpStatus.CREATED).location(EntityURLBuilder.buildURL(MEASUREMENT_PATH, measurementService.add(Measurement.builder().date(measurement.getDateTime()).kw(measurement.getKw()).meter(meter).build()).getMeasurementId())).build();
        }else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Meter");
        }


    }
}