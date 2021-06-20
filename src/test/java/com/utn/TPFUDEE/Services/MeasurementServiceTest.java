package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Models.Measurement;
import com.utn.TPFUDEE.Models.Projections.MeasurementProjection;
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
    private MeasurementRepository measurementRepositoryMock;
    private Measurement measurement;
    @Mock
    private MeasurementProjection measurementProjection;


    @BeforeAll
    public void setUp(){
        measurementRepositoryMock = mock(MeasurementRepository.class);
        measurementService = new MeasurementService(measurementRepositoryMock);
        measurement = new Measurement(1, null, null, null, null, null);
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
    public void getPage_MeasurementNotFound(){
        Pageable pageable = PageRequest.of(0, 1);
        Mockito.when(measurementRepositoryMock.findAll(pageable)).thenReturn(Page.empty());

        Assertions.assertThrows(ResponseStatusException.class, () ->{
            measurementService.getAll(pageable);
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
//Arreglar
//    @Test
//    public void getAllByDateTest(){
//        Pageable pageable = PageRequest.of(0, 1);
//        List<MeasurementProjection> list = new ArrayList<>();
//        list.add(measurementProjection);
//        Page<MeasurementProjection> measurementProjectionPage = new PageImpl<>(list, pageable, pageable.getPageSize());
//        Mockito.when(measurementRepositoryMock.findByMeterAndDateBetween(measurementProjection.getId(),null, null,pageable)).thenReturn(measurementProjectionPage);
//
//        Page<MeasurementProjection> result = measurementService.getAllByDate(measurementProjection.getId(),"null", "null",pageable);
//
//        Assertions.assertNotNull(result);
//    }

}
