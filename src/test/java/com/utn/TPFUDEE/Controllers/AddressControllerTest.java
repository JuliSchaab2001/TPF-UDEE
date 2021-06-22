package com.utn.TPFUDEE.Controllers;
import com.utn.TPFUDEE.Models.Address;
import com.utn.TPFUDEE.Models.Client;
import com.utn.TPFUDEE.Models.DTO.UserDTO;
import com.utn.TPFUDEE.Models.Projections.AddressProjection;
import com.utn.TPFUDEE.Models.User;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;

import static org.mockito.Mockito.*;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class AddressControllerTest {

    private Authentication auth;
    private AddressController addressController;
    private AddressService addressService;
    @Mock
    private MeasurementService measurementService;
    @Mock
    private BillService billService;
    private UserService userServiceMock;
    private Address address;
    private ResponseStatusException exception;
    @Mock
    AddressProjection addressProjection;
    Client client;
    User user;


    /*@BeforeAll
    public void setUp(){
        auth = mock(Authentication.class);
        addressService = mock(AddressService.class);
        userServiceMock = mock(UserService.class);
        addressController = new AddressController(addressService, measurementService, billService, userServiceMock);
        exception = null;
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
    }*/
}
