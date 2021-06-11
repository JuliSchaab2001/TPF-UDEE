package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Models.Tariff;
import com.utn.TPFUDEE.Repositories.TariffRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.mock;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class TariffServiceTest {

    private TariffService tariffService;
    private TariffRepository tariffRepositoryMock;

    @BeforeAll
    public void setUp(){
        tariffRepositoryMock = mock(TariffRepository.class);
        tariffService = new TariffService(tariffRepositoryMock);
    }

    @Test
    public void getById_ReturnTariff(){
        Integer id = 1;
        Tariff tariff = new Tariff(id, null, null, null);
        Mockito.when(tariffRepositoryMock.findById(id)).thenReturn(Optional.of(tariff));

        Tariff result = tariffService.getById(id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(tariff, result);
    }

}
