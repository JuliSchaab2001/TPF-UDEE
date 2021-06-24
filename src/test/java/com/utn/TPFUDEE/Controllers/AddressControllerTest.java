package com.utn.TPFUDEE.Controllers;
import com.utn.TPFUDEE.Models.Address;
import com.utn.TPFUDEE.Models.Client;
import com.utn.TPFUDEE.Models.DTO.UserDTO;
import com.utn.TPFUDEE.Models.Measurement;
import com.utn.TPFUDEE.Models.Projections.AddressProjection;
import com.utn.TPFUDEE.Models.Projections.BillProjection;
import com.utn.TPFUDEE.Models.Projections.MeasurementProjection;
import com.utn.TPFUDEE.Models.Projections.MoneyAndKwProjection;
import com.utn.TPFUDEE.Models.User;
import com.utn.TPFUDEE.Repositories.MeasurementRepository;
import com.utn.TPFUDEE.Services.AddressService;
import com.utn.TPFUDEE.Services.BillService;
import com.utn.TPFUDEE.Services.MeasurementService;
import com.utn.TPFUDEE.Services.UserService;
import com.utn.TPFUDEE.Utils.EntityURLBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class AddressControllerTest {

    private Authentication auth;
    private AddressController addressController;
    private AddressService addressService;
    private MeasurementService measurementService;
    private BillService billService;
    private UserService userServiceMock;
    private Address address;
    @Mock
    AddressProjection addressProjection;
    @Mock
    MoneyAndKwProjection moneyAndKwProjection;
    @Mock
    MeasurementProjection measurementProjection;
    @Mock
    BillProjection billProjection;
    Client client;
    User user;


    @BeforeAll
    public void setUp(){
        auth = mock(Authentication.class);
        addressService = mock(AddressService.class);
        billService = mock(BillService.class);
        userServiceMock = mock(UserService.class);
        measurementService = mock(MeasurementService.class);
        addressController = new AddressController(addressService, measurementService, billService, userServiceMock);
        user = new User(1, null, null, true, client);
        client = new Client(1, null, null, null, null, user);
        address = new Address (1, null, null, null, client, null);
    }

    @Test
    public void addTest_StatusCreated(){
        Mockito.when(addressService.add(address)).thenReturn(address);
        validateRol_IsEmployee();

        try(MockedStatic<EntityURLBuilder> entityURLBuilderMockedStatic = Mockito.mockStatic(EntityURLBuilder.class)){
            entityURLBuilderMockedStatic.when(() -> EntityURLBuilder.buildURL(AddressController.ADDRESS_PATH, address.getAddressId())).thenReturn(URI.create("a"));

            ResponseEntity result = addressController.add(auth, address);

            Assertions.assertEquals(HttpStatus.CREATED.value(), result.getStatusCodeValue());
        }
    }

    @Test
    public void addTest_StatusForbidden(){
        validateRol_IsClient();

        Assertions.assertThrows(ResponseStatusException.class, () -> addressController.add(auth, address));
    }

    @Test
    public void getByIdTest_StatusOk(){
        Integer id = 1;
        this.validate_IsEmployee();
        Mockito.when(addressService.getOnlyAddressById(id)).thenReturn(addressProjection);

        ResponseEntity<AddressProjection> result = addressController.getById(auth, id);

        Assertions.assertEquals(addressProjection, result.getBody());
        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
    }

    @Test
    public void getByIdTest_StatusForbidden(){
        validate_IsClient();

        Assertions.assertThrows(ResponseStatusException.class, () -> addressController.getById(auth, address.getAddressId()));
    }

    @Test
    public void getAddressConsumesTest_StatusOk(){
        Integer id = 1;
        LocalDate date = LocalDate.now();
        validate_IsEmployee();
        Mockito.when(measurementService.getAddressConsumes(id, date, date)).thenReturn(moneyAndKwProjection);

        ResponseEntity<MoneyAndKwProjection> result = addressController.getAddressConsumes(auth, id, date.toString(), date.toString());

        Assertions.assertEquals(moneyAndKwProjection, result.getBody());
        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
    }

    @Test
    public void getAddressConsumesTest_StatusForbidden(){
        validate_IsClient();
        String date = "2020-10-01";

        Assertions.assertThrows(ResponseStatusException.class, () -> addressController.getAddressConsumes(auth, address.getAddressId(), date, date));
    }

    @Test
    public void getAddressBillUnpaidTest_StatusOk(){
        Integer id = 1;
        Pageable pageable = PageRequest.of(0, 1);
        List<BillProjection> list = new ArrayList<>();
        list.add(billProjection);
        Page<BillProjection> billProjectionPage = new PageImpl<>(list, pageable, pageable.getPageSize());
        validate_IsEmployee();
        Mockito.when(billService.getUnPaidBillsByAddress(id, pageable)).thenReturn(billProjectionPage);

        ResponseEntity<List<BillProjection>> result = addressController.getAddressBillUnPaid(auth, id,0, 1);

        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
    }

    @Test
    public void getAddressBillUnpaidTest_StatusForbidden(){
        validate_IsClient();

        Assertions.assertThrows(ResponseStatusException.class, () -> addressController.getAddressBillUnPaid(auth, address.getAddressId(), 0, 1));
    }

    @Test
    public void getBillByDatesTest_StatusOk(){
        Integer id = 1;
        Pageable pageable = PageRequest.of(0, 1);
        List<BillProjection> list = new ArrayList<>();
        list.add(billProjection);
        Page<BillProjection> billProjectionPage = new PageImpl<>(list, pageable, pageable.getPageSize());
        validate_IsEmployee();
        Mockito.when(billService.getBillsByDates(id, LocalDate.now(), LocalDate.now(), pageable)).thenReturn(billProjectionPage);

        ResponseEntity<List<BillProjection>> result = addressController.getBillsByDates(auth, id, LocalDate.now().toString(), LocalDate.now().toString(),0, 1);

        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
    }

    @Test
    public void getBillByDatesTest_StatusForbidden(){
        validate_IsClient();
        String date = "2020-10-01";

        Assertions.assertThrows(ResponseStatusException.class, () -> addressController.getBillsByDates(auth, address.getAddressId(), LocalDate.now().toString(), LocalDate.now().toString(), 0, 1));
    }

    @Test
    public void getAddressMeasurementTest_StatusOk(){
        Integer id = 1;
        String SDate = "2020-10-01";
        Pageable pageable = PageRequest.of(0, 1);
        List<MeasurementProjection> list = new ArrayList<>();
        list.add(measurementProjection);
        Page<MeasurementProjection> measurementProjectionPage = new PageImpl<>(list, pageable, pageable.getPageSize());
        validate_IsEmployee();

        Mockito.when(measurementService.getAllByDate(id, SDate, SDate, pageable)).thenReturn(measurementProjectionPage);

        ResponseEntity<List<MeasurementProjection>> result = addressController.getAddressMeasurement(auth, id, SDate, SDate, 0, 1);

        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
    }

    @Test
    public void getAddressMeasurementTest_StatusForbidden(){
        validate_IsClient();
        String date = "2020-10-01";

        Assertions.assertThrows(ResponseStatusException.class, () -> addressController.getAddressMeasurement(auth, address.getAddressId(), date, date, 0, 10));
    }

    @Test
    public void deleteByIdTest_StatusCreated(){
        Mockito.when(addressService.deleteById(address.getAddressId())).thenReturn(address.getAddressId());
        validateRol_IsEmployee();

        ResponseEntity result = addressController.deleteById(auth, address.getAddressId());

        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
    }

    @Test
    public void deleteByIdTest_StatusForbidden(){
        validateRol_IsClient();

        Assertions.assertThrows(ResponseStatusException.class, () -> addressController.deleteById(auth, 1));
    }

    @Test
    public void updateTest_StatusOk(){
        Mockito.when(addressService.update(address)).thenReturn(address);
        validateRol_IsEmployee();

        try(MockedStatic<EntityURLBuilder> entityURLBuilderMockedStatic = Mockito.mockStatic(EntityURLBuilder.class)){
            entityURLBuilderMockedStatic.when(() -> EntityURLBuilder.buildURL(AddressController.ADDRESS_PATH, address.getAddressId())).thenReturn(URI.create("a"));

            ResponseEntity result = addressController.update(auth, address);

            Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
        }
    }

    @Test
    public void updateTest_StatusForbidden(){
        validateRol_IsClient();

        Assertions.assertThrows(ResponseStatusException.class, () -> addressController.update(auth, address));
    }

    private void validate_IsEmployee(){
        Integer id = 1;
        Mockito.when(auth.getPrincipal()).thenReturn(UserDTO.builder().userId(user.getUserId()).build());
        Mockito.when(userServiceMock.getById(user.getUserId())).thenReturn(user);
        Mockito.when(addressService.getById(id)).thenReturn(address);
    }

    private void validate_IsClient(){
        Integer id = 1;
        User user = new User(2, null, null, false, client);
        Mockito.when(auth.getPrincipal()).thenReturn(UserDTO.builder().userId(user.getUserId()).build());
        Mockito.when(userServiceMock.getById(user.getUserId())).thenReturn(user);
        Mockito.when(addressService.getById(id)).thenReturn(address);
    }

    private void validateRol_IsEmployee(){
        Mockito.when(auth.getPrincipal()).thenReturn(UserDTO.builder().userId(user.getUserId()).build());
        Mockito.when(userServiceMock.getById(user.getUserId())).thenReturn(user);
    }
    private void validateRol_IsClient(){
        User user = new User(1, null, null, false, null);
        Mockito.when(auth.getPrincipal()).thenReturn(UserDTO.builder().userId(user.getUserId()).build());
        Mockito.when(userServiceMock.getById(user.getUserId())).thenReturn(user);
    }
}
