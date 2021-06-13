package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Models.Address;
import com.utn.TPFUDEE.Models.Measurement;
import com.utn.TPFUDEE.Repositories.AddressRepository;
import com.utn.TPFUDEE.Repositories.MeasurementRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.mock;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class MeasurementServiceTest {

    private MeasurementService measurementService;
    private MeasurementRepository measurementRepositoryMock;
    private Measurement measurement;


    @BeforeAll
    public void setUp(){
        measurementRepositoryMock = mock(MeasurementRepository.class);
        measurementService = new MeasurementService(measurementRepositoryMock);
        measurement = new Measurement(1, null, null, null, null, null);
    }

    @Test
    public void getById_ReturnMeasurement(){
        Integer id = 1;
        Mockito.when(measurementRepositoryMock.findById(id)).thenReturn(Optional.of(measurement));

        Measurement result = measurementService.getById(id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(measurement, result);
    }

    @Test
    public void Add_ReturnMeasurement(){
        Mockito.when(measurementRepositoryMock.save(measurement)).thenReturn(measurement);

        Measurement result = measurementService.add(measurement);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(measurement, result);
    }

}
