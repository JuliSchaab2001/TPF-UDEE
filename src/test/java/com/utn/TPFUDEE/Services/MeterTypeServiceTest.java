package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Models.Meter;
import com.utn.TPFUDEE.Models.MeterType;
import com.utn.TPFUDEE.Repositories.MeterTypeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.mock;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class MeterTypeServiceTest {

    private MeterTypeService meterTypeService;
    private MeterTypeRepository meterTypeRepositoryMock;
    private MeterType meterType;


    @BeforeAll
    public void setUp(){
        meterTypeRepositoryMock = mock(MeterTypeRepository.class);
        meterTypeService = new MeterTypeService(meterTypeRepositoryMock);
        meterType = new MeterType(1, "a", "a", null);

    }

    @Test
    public void getById_ReturnMeterType(){
        Integer id = 1;
        Mockito.when(meterTypeRepositoryMock.findById(id)).thenReturn(Optional.of(meterType));

        MeterType result = meterTypeService.getById(id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(meterType, result);
    }

    @Test
    public void Add_ReturnMeterType(){
        Mockito.when(meterTypeRepositoryMock.save(meterType)).thenReturn(meterType);

        MeterType result = meterTypeService.add(meterType);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(meterType, result);
    }

}
