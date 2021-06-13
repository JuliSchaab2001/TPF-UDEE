package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Models.Address;
import com.utn.TPFUDEE.Models.Bill;
import com.utn.TPFUDEE.Repositories.BillRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.mock;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class BillServiceTest {

    private BillService billService;
    private BillRepository billRepositoryMock;
    private Bill bill;


    @BeforeAll
    public void setUp(){
        billRepositoryMock = mock(BillRepository.class);
        billService = new BillService(billRepositoryMock);
        bill = new Bill(1, 1, 1, null, null, null, null, 1, null, null, null);
    }

    @Test
    public void getById_ReturnBill(){
        //Arrange
        Integer id = 1;
        Mockito.when(billRepositoryMock.findById(id)).thenReturn(Optional.of(bill));
        //Act
        Bill result = billService.getById(id);
        //Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(bill, result);
    }


}
