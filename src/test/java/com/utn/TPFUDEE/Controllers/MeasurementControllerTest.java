package com.utn.TPFUDEE.Controllers;

import com.utn.TPFUDEE.Models.DTO.MeasurementDTO;
import com.utn.TPFUDEE.Models.Measurement;
import com.utn.TPFUDEE.Models.Meter;
import com.utn.TPFUDEE.Services.MeasurementService;
import com.utn.TPFUDEE.Services.MeterService;
import com.utn.TPFUDEE.Utils.EntityURLBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;

import static org.mockito.Mockito.mock;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class MeasurementControllerTest {

    private MeasurementController measurementController;
    private MeasurementService measurementService;
    private MeterService meterService;
    private MeasurementDTO measurementDto;
    private Measurement measurement;
    Meter meter;


    @BeforeAll
    public void setUp(){
        measurementService = mock(MeasurementService.class);
        meterService = mock(MeterService.class);
        measurementController = new MeasurementController(measurementService, meterService);
        meter = new Meter(1, "aaaa1111", "a", null, null, null, null);
        measurementDto = new MeasurementDTO("a", "aaaa1111", null, 2);
        measurement = new Measurement(1, measurementDto.getDateTime(), measurementDto.getKw(), null, meter, null);
    }

    @Test
    public void addTest_StatusCreated(){
        Mockito.when(meterService.getBySerialNumber(measurementDto.getSerialNumber())).thenReturn(meter);
        Mockito.when(measurementService.add(Measurement.builder().date(measurementDto.getDateTime()).kw(measurementDto.getKw()).meter(meter).build())).thenReturn(measurement);

        try(MockedStatic<EntityURLBuilder> entityURLBuilderMockedStatic = Mockito.mockStatic(EntityURLBuilder.class)){
            entityURLBuilderMockedStatic.when(() -> EntityURLBuilder.buildURL(MeasurementController.MEASUREMENT_PATH, (Measurement.builder().date(measurementDto.getDateTime()).kw(measurementDto.getKw()).meter(meter).build()).getMeasurementId())).thenReturn(URI.create("a"));

            ResponseEntity result = measurementController.add(measurementDto);

            Assertions.assertEquals(HttpStatus.CREATED.value(), result.getStatusCodeValue());
        }
    }

    @Test
    public void addTest_MeterNull_StatusForbidden(){
        Mockito.when(meterService.getBySerialNumber(measurementDto.getSerialNumber())).thenReturn(null);
        Assertions.assertThrows(ResponseStatusException.class, () -> measurementController.add(measurementDto));
    }

    @Test
    public void addTest_InvalidPass_StatusForbidden(){
        Meter meter = new Meter(1, "aaaa1111", "33", null, null, null, null);

        Mockito.when(meterService.getBySerialNumber(measurementDto.getSerialNumber())).thenReturn(meter);
        Assertions.assertThrows(ResponseStatusException.class, () -> measurementController.add(measurementDto));
    }
}
