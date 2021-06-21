package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Models.Address;
import com.utn.TPFUDEE.Models.Measurement;
import com.utn.TPFUDEE.Models.Meter;
import com.utn.TPFUDEE.Models.Projections.MeasurementProjection;
import com.utn.TPFUDEE.Repositories.AddressRepository;
import com.utn.TPFUDEE.Repositories.MeasurementRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class MeasurementServiceTest {

    private MeasurementService measurementService;
    private AddressService addressService;
    private MeasurementRepository measurementRepositoryMock;
    private Measurement measurement;
    private Meter meter;
    @Mock
    private MeasurementProjection measurementProjection;

    @BeforeAll
    public void setUp(){
        measurementRepositoryMock = mock(MeasurementRepository.class);
        addressService = mock(AddressService.class);
        measurementService = new MeasurementService(measurementRepositoryMock, addressService);
        measurement = new Measurement(1, null, null, null, null, null);
        meter = new Meter(1, "aaaa1111", "aaaa", null, null, null, null);

    }

    @Test
    public void getByIdTest_ReturnMeasurement(){
        Integer id = 1;
        Mockito.when(measurementRepositoryMock.findById(id)).thenReturn(Optional.of(measurement));

        Measurement result = measurementService.getById(id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(measurement, result);
    }

    @Test
    public void getByIdTest_MeasurementNotFound(){
        Mockito.when(measurementRepositoryMock.findById(measurement.getMeasurementId())).thenReturn(Optional.empty());

        Assertions.assertThrows(ResponseStatusException.class, () ->{
            measurementService.getById(measurement.getMeasurementId());
        });
    }

    @Test
    public void getPageTest(){
        Pageable pageable = PageRequest.of(0, 1);
        List<Measurement> list = new ArrayList<>();
        list.add(measurement);
        Page<Measurement> measurementPage = new PageImpl<>(list, pageable, pageable.getPageSize());
        Mockito.when(measurementRepositoryMock.findAll(pageable)).thenReturn(measurementPage);

        Page<Measurement> result = measurementService.getAll(pageable);

        Assertions.assertNotNull(result);
    }

    @Test
    public void getPage_NoContent(){
        Pageable pageable = PageRequest.of(0, 1);
        Mockito.when(measurementRepositoryMock.findAll(pageable)).thenReturn(Page.empty());

        Assertions.assertThrows(ResponseStatusException.class, () ->{
            measurementService.getAll(pageable);
        });
    }

    @Test
    public void getAllByDateTest_ReturnPage(){
        Integer id = 1;
        Address address = new Address(id, "null", 1, null, null, meter);
        String date = LocalDate.now().toString();
        Pageable pageable = PageRequest.of(0, 10);
        List<MeasurementProjection> list = new ArrayList<>();
        list.add(measurementProjection);
        Page<MeasurementProjection> measurementProjectionPage = new PageImpl<>(list, pageable, pageable.getPageSize());
        Mockito.when(addressService.getById(id)).thenReturn(address);
        Mockito.when(measurementRepositoryMock.findByMeterIdAndDateBetween(meter.getMeterId(), date, date, pageable)).thenReturn(measurementProjectionPage);

        Page<MeasurementProjection> result = measurementService.getAllByDate(meter.getMeterId(), date, date, pageable);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(measurementProjection, result.getContent().get(0));
    }

    @Test
    public void getAllByDate_NotFound(){
        Pageable pageable = PageRequest.of(0, 1);
        Address address = new Address(1, "null", 1, null, null, null);
        String date = LocalDate.now().toString();
        Mockito.when(addressService.getById(1)).thenReturn(address);

        Assertions.assertThrows(ResponseStatusException.class, () ->{
            measurementService.getAllByDate(1, date, date, pageable);
        });
    }

    @Test
    public void getAllByDate_NoContent(){
        Pageable pageable = PageRequest.of(0, 1);
        String date = LocalDate.now().toString();
        Address address = new Address(1, "null", 1, null, null, meter);
        Mockito.when(addressService.getById(1)).thenReturn(address);
        Mockito.when(measurementRepositoryMock.findByMeterIdAndDateBetween(meter.getMeterId(), date, date, pageable)).thenReturn(Page.empty());

        Assertions.assertThrows(ResponseStatusException.class, () ->{
            measurementService.getAllByDate(1, date, date, pageable);
        });
    }

    @Test
    public void addTest(){
        Mockito.when(measurementRepositoryMock.save(measurement)).thenReturn(measurement);
        Mockito.when(measurementRepositoryMock.findById(measurement.getMeasurementId())).thenReturn(Optional.empty());

        Measurement result = measurementService.add(measurement);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(measurement, result);
    }

    @Test
    public void deleteTest(){
        Mockito.when(measurementRepositoryMock.findById(measurement.getMeasurementId())).thenReturn(Optional.of(measurement));

        Integer result = measurementService.deleteById(measurement.getMeasurementId());
        Assertions.assertNotNull(result);
    }

}
