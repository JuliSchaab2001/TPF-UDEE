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

    @BeforeAll
    public void setUp(){
        measurementRepositoryMock = mock(MeasurementRepository.class);
        measurementService = new MeasurementService(measurementRepositoryMock);
    }

    @Test
    public void getById_ReturnMeasurement(){
        Integer id = 1;
        Measurement measurement = new Measurement(id, null, null, null, null, null);
        Mockito.when(measurementRepositoryMock.findById(id)).thenReturn(Optional.of(measurement));

        Measurement result = measurementService.getById(id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(measurement, result);
    }

}
