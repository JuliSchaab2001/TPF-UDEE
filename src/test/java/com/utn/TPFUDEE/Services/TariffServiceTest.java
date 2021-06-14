package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Models.Address;
import com.utn.TPFUDEE.Models.Client;
import com.utn.TPFUDEE.Models.Tariff;
import com.utn.TPFUDEE.Repositories.TariffRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class TariffServiceTest {

    private TariffService tariffService;
    private TariffRepository tariffRepositoryMock;
    private Tariff tariff;

    @BeforeAll
    public void setUp(){
        tariffRepositoryMock = mock(TariffRepository.class);
        tariffService = new TariffService(tariffRepositoryMock);
        tariff = new Tariff(1, null, null, null);
    }

    @Test
    public void getByIdTest_ReturnTariff(){
        Integer id = 1;
        Mockito.when(tariffRepositoryMock.findById(id)).thenReturn(Optional.of(tariff));

        Tariff result = tariffService.getById(id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(tariff, result);
    }

    @Test
    public void getByIdTest_TariffNotFound(){
        Mockito.when(tariffRepositoryMock.findById(tariff.getTariffId())).thenReturn(Optional.empty());

        Assertions.assertThrows(ResponseStatusException.class, () ->{
            tariffService.getById(tariff.getTariffId());
        });
    }

    @Test
    public void getPageTest(){
        Pageable pageable = PageRequest.of(0, 1);
        List<Tariff> list = new ArrayList<>();
        list.add(tariff);
        Page<Tariff> tariffPage = new PageImpl<>(list, pageable, pageable.getPageSize());
        Mockito.when(tariffRepositoryMock.findAll(pageable)).thenReturn(tariffPage);

        Page<Tariff> result = tariffService.getAll(pageable);

        Assertions.assertNotNull(result);
    }

    @Test
    public void addTest() {
        Mockito.when(tariffRepositoryMock.save(tariff)).thenReturn(tariff);
        Mockito.when(tariffRepositoryMock.findById(tariff.getTariffId())).thenReturn(Optional.empty());

        Tariff result = tariffService.add(tariff);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(tariff, result);
    }

}
