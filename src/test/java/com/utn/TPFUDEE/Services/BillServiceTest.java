package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Models.Address;
import com.utn.TPFUDEE.Models.Bill;
import com.utn.TPFUDEE.Models.Client;
import com.utn.TPFUDEE.Models.Meter;
import com.utn.TPFUDEE.Models.Projections.BillProjection;
import com.utn.TPFUDEE.Repositories.BillRepository;
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
public class BillServiceTest {

    private BillService billService;
    private AddressService addressService;
    private ClientService clientService;
    @Mock
    private MeterService meterService;
    @Mock
    private BillProjection billProjection;
    private BillRepository billRepositoryMock;
    private Bill bill;
    private Client client;
    private Meter meter;


    /*@BeforeAll
    public void setUp(){
        billRepositoryMock = mock(BillRepository.class);
        clientService = mock(ClientService.class);
        addressService = mock(AddressService.class);
        billService = new BillService(billRepositoryMock, addressService, clientService, meterService);
        bill = new Bill(1, 1.1, 1.1, null, null, null, null, 1, null, null, null);
        client = new Client(1, "aaa", "aaa", "aaa", null, null);
        meter = new Meter(1, "aaaa1111", "aaaa", null, null, null, null);

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

        Assertions.assertThrows(ResponseStatusException.class, () -> billService.getById(bill.getBillId()));
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
    public void getPageTest_NoContent(){
        Pageable pageable = PageRequest.of(0, 1);
        Mockito.when(billRepositoryMock.findAll(pageable)).thenReturn(Page.empty());

        Assertions.assertThrows(ResponseStatusException.class, () -> billService.getAll(pageable));
    }

    @Test
    public void getUnpaidBillsByAddressPageTest_ReturnPage(){
        Integer id = 1;
        Address address = new Address(id, "null", 1, null, null, meter);
        Pageable pageable = PageRequest.of(0, 10);
        List<BillProjection> list = new ArrayList<>();
        list.add(billProjection);
        Page<BillProjection> billProjectionPage = new PageImpl<>(list, pageable, pageable.getPageSize());
        Mockito.when(addressService.getById(id)).thenReturn(address);
        Mockito.when(billRepositoryMock.getBillByMeterAndIsPaid(meter.getMeterId(), false, pageable)).thenReturn(billProjectionPage);

        Page<BillProjection> result = billService.getUnPaidBillsByAddress(meter.getMeterId(), pageable);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(billProjection, result.getContent().get(0));
    }

    @Test
    public void getAllByDate_NotFound(){
        Pageable pageable = PageRequest.of(0, 1);
        Address address = new Address(1, "null", 1, null, null, null);
        Mockito.when(addressService.getById(1)).thenReturn(address);

        Assertions.assertThrows(ResponseStatusException.class, () -> billService.getUnPaidBillsByAddress(meter.getMeterId(), pageable));
    }

    @Test
    public void getAllByDate_NoContent(){
        Pageable pageable = PageRequest.of(0, 1);
        Address address = new Address(1, "null", 1, null, null, meter);
        Mockito.when(addressService.getById(1)).thenReturn(address);
        Mockito.when(billRepositoryMock.getBillByMeterAndIsPaid(meter.getMeterId(), false, pageable)).thenReturn(Page.empty());

        Assertions.assertThrows(ResponseStatusException.class, () -> billService.getUnPaidBillsByAddress(1, pageable));
    }
    @Test
    public void getUnpaidBillsByClientPageTest(){
        Pageable pageable = PageRequest.of(0, 1);
        List<BillProjection> list = new ArrayList<>();
        list.add(billProjection);
        Page<BillProjection> billProjectionPage = new PageImpl<>(list, pageable, pageable.getPageSize());
        Mockito.when(clientService.getById(client.getDni())).thenReturn(client);
        Mockito.when(billRepositoryMock.getBillByMeterInAndIsPaid(client.getDni(), false, pageable)).thenReturn(billProjectionPage);

        Page<BillProjection> result = billService.getUnPaidBillsByClient(client.getDni(), pageable);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(billProjection, result.getContent().get(0));
    }

    @Test
    public void getUnpaidBillsByClientPageTest_NotFound(){
        Pageable pageable = PageRequest.of(0, 1);
        Mockito.when(clientService.getById(1)).thenReturn(null);

        Assertions.assertThrows(ResponseStatusException.class, () -> billService.getUnPaidBillsByClient(1, pageable));
    }

    @Test
    public void getBillByDatesPageTest(){
        LocalDate date = LocalDate.now();
        Pageable pageable = PageRequest.of(0, 1);
        List<BillProjection> list = new ArrayList<>();
        list.add(billProjection);
        Page<BillProjection> billProjectionPage = new PageImpl<>(list, pageable, pageable.getPageSize());
        Mockito.when(billRepositoryMock.getBillByBillIdAndFinalDateBetween(bill.getBillId(), date.atTime(0, 0, 0), date.atTime(0, 0, 0), pageable)).thenReturn(billProjectionPage);

        Page<BillProjection> result = billService.getBillsByDates(bill.getBillId(), date, date, pageable);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(billProjection, result.getContent().get(0));
    }

    @Test
    public void deleteTest(){
        Mockito.when(billRepositoryMock.findById(bill.getBillId())).thenReturn(Optional.of(bill));

        Integer result = billService.deleteById(bill.getBillId());
        Assertions.assertNotNull(result);
    }
*/
}
