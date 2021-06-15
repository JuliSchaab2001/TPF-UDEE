package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Models.Address;
import com.utn.TPFUDEE.Models.Bill;
import com.utn.TPFUDEE.Repositories.BillRepository;
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
    public void getByIdTest_ReturnBill(){
        //Arrange
        Integer id = 1;
        Mockito.when(billRepositoryMock.findById(id)).thenReturn(Optional.of(bill));
        //Act
        Bill result = billService.getById(id);
        //Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(bill, result);
    }

    @Test
    public void getByIdTest_BillNotFound(){
        Mockito.when(billRepositoryMock.findById(bill.getBillId())).thenReturn(Optional.empty());

        Assertions.assertThrows(ResponseStatusException.class, () ->{
            billService.getById(bill.getBillId());
        });
    }
    @Test
    public void getPageTest(){
        Pageable pageable = PageRequest.of(0, 1);
        List<Bill> list = new ArrayList<>();
        list.add(bill);
        Page<Bill> billPage = new PageImpl<>(list, pageable, pageable.getPageSize());
        Mockito.when(billRepositoryMock.findAll(pageable)).thenReturn(billPage);

        Page<Bill> result = billService.getAll(pageable);

        Assertions.assertNotNull(result);
    }

    @Test
    public void getPage_BillNotFound(){
        Pageable pageable = PageRequest.of(0, 1);
        Mockito.when(billRepositoryMock.findAll(pageable)).thenReturn(Page.empty());

        Assertions.assertThrows(ResponseStatusException.class, () ->{
            billService.getAll(pageable);
        });
    }


}
