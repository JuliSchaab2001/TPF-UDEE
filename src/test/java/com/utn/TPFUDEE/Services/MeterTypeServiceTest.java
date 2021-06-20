package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Models.MeterType;
import com.utn.TPFUDEE.Repositories.MeterTypeRepository;
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
    public void getByIdTest_ReturnMeterType(){
        Integer id = 1;
        Mockito.when(meterTypeRepositoryMock.findById(id)).thenReturn(Optional.of(meterType));

        MeterType result = meterTypeService.getById(id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(meterType, result);
    }

    @Test
    public void getByIdTest_MeterTypeNotFound(){
        Mockito.when(meterTypeRepositoryMock.findById(meterType.getMeterTypeId())).thenReturn(Optional.empty());

        Assertions.assertThrows(ResponseStatusException.class, () ->{
            meterTypeService.getById(meterType.getMeterTypeId());
        });
    }

    @Test
    public void getPageTest(){
        Pageable pageable = PageRequest.of(0, 1);
        List<MeterType> list = new ArrayList<>();
        list.add(meterType);
        Page<MeterType> meterTypePage = new PageImpl<>(list, pageable, pageable.getPageSize());
        Mockito.when(meterTypeRepositoryMock.findAll(pageable)).thenReturn(meterTypePage);

        Page<MeterType> result = meterTypeService.getAll(pageable);

        Assertions.assertNotNull(result);
    }

    @Test
    public void getPageTest_MeterTypeNotFound(){
        Pageable pageable = PageRequest.of(0, 1);
        Mockito.when(meterTypeRepositoryMock.findAll(pageable)).thenReturn(Page.empty());

        Assertions.assertThrows(ResponseStatusException.class, () ->{
            meterTypeService.getAll(pageable);
        });
    }

    @Test
    public void addTest(){
        Mockito.when(meterTypeRepositoryMock.save(meterType)).thenReturn(meterType);
        Mockito.when(meterTypeRepositoryMock.findByModelAndBrand(meterType.getModel(), meterType.getBrand())).thenReturn(null);

        MeterType result = meterTypeService.add(meterType);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(meterType, result);
    }

    @Test
    public void addTest_MeterTypeExist(){
        Mockito.when(meterTypeRepositoryMock.findByModelAndBrand(meterType.getModel(), meterType.getBrand())).thenReturn(meterType);

        Assertions.assertThrows(ResponseStatusException.class, () ->{
            meterTypeService.add(meterType);
        });
    }

    @Test
    public void deleteTest(){
        Mockito.when(meterTypeRepositoryMock.findById(meterType.getMeterTypeId())).thenReturn(Optional.of(meterType));

        Integer result = meterTypeService.deleteById(meterType.getMeterTypeId());
        Assertions.assertNotNull(result);
    }

}
