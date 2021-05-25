package com.utn.TPFUDEE.Controllers;


import com.utn.TPFUDEE.Exceptions.Exist.MeasurementExistException;
import com.utn.TPFUDEE.Exceptions.NoContent.MeasurementNoContentException;
import com.utn.TPFUDEE.Exceptions.NotFound.MeasurementNotFoundException;
import com.utn.TPFUDEE.Models.DTO.MeasurementDTO;
import com.utn.TPFUDEE.Models.Measurement;
import com.utn.TPFUDEE.Models.Meter;
import com.utn.TPFUDEE.Services.MeasurementService;
import com.utn.TPFUDEE.Services.MeterService;
import com.utn.TPFUDEE.Utils.EntityURLBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    private static final String MEASUREMENT_PATH = "measurement";

    @Autowired
    private MeasurementService measurementService;
    private MeterService meterService;

    @PostMapping("/")
    public ResponseEntity add(@RequestBody MeasurementDTO measurement) throws ResponseStatusException, MeasurementExistException {
        Meter meter= meterService.getBySerialNumber(measurement.getSerialNumber());

        if(meter.getPassword().equals(measurement.getPassword())) {
            return ResponseEntity.status(HttpStatus.CREATED).location(EntityURLBuilder.buildURL(MEASUREMENT_PATH, measurementService.add(Measurement.builder().dateTime(measurement.getDate()).kw(measurement.getKw()).meter(meter).build()).getMeasurement_id())).build();
        }else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Meter Password");
        }


    }
}