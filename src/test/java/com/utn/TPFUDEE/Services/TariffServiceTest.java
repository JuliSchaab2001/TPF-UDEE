package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Models.Client;
import com.utn.TPFUDEE.Models.Tariff;
import com.utn.TPFUDEE.Repositories.TariffRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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
    public void getById_ReturnTariff(){
        Integer id = 1;
        Mockito.when(tariffRepositoryMock.findById(id)).thenReturn(Optional.of(tariff));

        Tariff result = tariffService.getById(id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(tariff, result);
    }

    @Test
    public void Add_ReturnClient(){
        Mockito.when(tariffRepositoryMock.save(tariff)).thenReturn(tariff);
        ResponseStatusException expectedExc = new ResponseStatusException(HttpStatus.CONFLICT, "Client Already Exist");
        try{
            Tariff result = tariffService.add(tariff);

            Assertions.assertNotNull(result);
            Assertions.assertEquals(tariff, result);
        }catch (ResponseStatusException e){
            e = expectedExc;
        }
    }
//Lo toy haciendo como el ojete

/*    @Test
    public void Add_NotReturnClient(){
        ResponseStatusException expectedExc = null;
        Mockito.when(tariffRepositoryMock.save(tariff)).thenReturn(null);
        try{
            Tariff result = tariffService.add(tariff);

            Assertions.assertNotNull(result);
            Assertions.assertNotEquals(tariff, result);
        }catch (ResponseStatusException e){
            expectedExc = e;
        }

        Assertions.assertEquals(HttpStatus.CONFLICT, expectedExc.getStatus());
        Assertions.assertTrue(expectedExc instanceof ResponseStatusException);
    }*/

}
