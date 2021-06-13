package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Models.Measurement;
import com.utn.TPFUDEE.Models.Meter;
import com.utn.TPFUDEE.Repositories.MeterRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.mock;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class MeterServiceTest {

    private MeterService meterService;
    private MeterRepository meterRepositoryMock;
    private Meter meter;


    @BeforeAll
    public void setUp(){
        meterRepositoryMock = mock(MeterRepository.class);
        meterService = new MeterService(meterRepositoryMock);
        meter = new Meter(1, "1", "1", null, null, null, null);
    }

    @Test
    public void getById_ReturnMeter(){
        Integer id = 1;
        Mockito.when(meterRepositoryMock.findById(id)).thenReturn(Optional.of(meter));

        Meter result = meterService.getById(id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(meter, result);
    }

    @Test
    public void Add_ReturnMeter(){
        Mockito.when(meterRepositoryMock.save(meter)).thenReturn(meter);

        Meter result = meterService.add(meter);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(meter, result);
    }

}
