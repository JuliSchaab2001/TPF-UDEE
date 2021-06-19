package com.utn.TPFUDEE.Services;


import com.utn.TPFUDEE.Models.Meter;
import com.utn.TPFUDEE.Repositories.MeterRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
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
    public void getByIdTest_ReturnMeter(){
        Integer id = 1;
        Mockito.when(meterRepositoryMock.findById(id)).thenReturn(Optional.of(meter));

        Meter result = meterService.getById(id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(meter, result);
    }

    @Test
    public void getBySerialNumberTest_ReturnMeter(){
        Mockito.when(meterRepositoryMock.findBySerialNumber(meter.getSerialNumber())).thenReturn(meter);

        Meter result = meterService.getBySerialNumber(meter.getSerialNumber());

        Assertions.assertNotNull(result);
        Assertions.assertEquals(meter, result);
    }

    @Test
    public void getByIdTest_MeterNotFound(){
        Mockito.when(meterRepositoryMock.findById(meter.getMeterId())).thenReturn(Optional.empty());

        Assertions.assertThrows(ResponseStatusException.class, () ->{
            meterService.getById(meter.getMeterId());
        });
    }

    @Test
    public void getPageTest(){
        Pageable pageable = PageRequest.of(0, 1);
        List<Meter> list = new ArrayList<>();
        list.add(meter);
        Page<Meter> meterPage = new PageImpl<>(list, pageable, pageable.getPageSize());
        Mockito.when(meterRepositoryMock.findAll(pageable)).thenReturn(meterPage);

        Page<Meter> result = meterService.getAll(pageable);

        Assertions.assertNotNull(result);
    }

    @Test
    public void getPageTest_MeterNotFound(){
        Pageable pageable = PageRequest.of(0, 1);
        Mockito.when(meterRepositoryMock.findAll(pageable)).thenReturn(Page.empty());

        Assertions.assertThrows(ResponseStatusException.class, () ->{
            meterService.getAll(pageable);
        });
    }

    @Test
    public void addTest(){
        Mockito.when(meterRepositoryMock.save(meter)).thenReturn(meter);
        Mockito.when(meterRepositoryMock.findBySerialNumber(meter.getSerialNumber())).thenReturn(null);

        Meter result = meterService.add(meter);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(meter, result);
    }

    @Test
    public void addTest_MeterExist(){
        Mockito.when(meterRepositoryMock.findBySerialNumber(meter.getSerialNumber())).thenReturn(meter);

        Assertions.assertThrows(ResponseStatusException.class, () ->{
            meterService.add(meter);
        });
    }

    @Test
    public void updateTest(){
        Mockito.when(meterRepositoryMock.findById(meter.getMeterId())).thenReturn(Optional.of(meter));
        Mockito.when(meterRepositoryMock.save(meter)).thenReturn(meter);

        Meter result = meterService.update(meter);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(meter, result);
    }

    @Test
    public void updateTest_MeterNotFound(){
        Mockito.when(meterRepositoryMock.findById(meter.getMeterId())).thenReturn(Optional.empty());

        Assertions.assertThrows(ResponseStatusException.class, () ->{
            meterService.getById(meter.getMeterId());
        });
    }

    @Test
    public void deleteTest(){
        Mockito.when(meterRepositoryMock.findById(meter.getMeterId())).thenReturn(Optional.of(meter));

        Integer result = meterService.deleteById(meter.getMeterId());
        Assertions.assertNotNull(result);
    }
}
