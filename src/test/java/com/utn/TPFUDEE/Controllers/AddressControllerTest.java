package com.utn.TPFUDEE.Controllers;

import com.utn.TPFUDEE.Models.Address;
import com.utn.TPFUDEE.Models.DTO.UserDTO;
import com.utn.TPFUDEE.Models.User;
import com.utn.TPFUDEE.Repositories.AddressRepository;
import com.utn.TPFUDEE.Services.AddressService;
import com.utn.TPFUDEE.Services.BillService;
import com.utn.TPFUDEE.Services.MeasurementService;
import com.utn.TPFUDEE.Services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class AddressControllerTest {

    @Mock
    private Authentication auth;
    private AddressController addressController;
    @Mock
    private AddressService addressService;
    @Mock
    private MeasurementService measurementService;
    @Mock
    private BillService billService;
    @Mock
    private UserService userService;

    private AddressRepository addressRepositoryMock;
    private ResponseStatusException exception;


    @BeforeAll
    public void setUp(){
        //auth = mock(Authentication.class);
        initMocks(this);
        addressController = new AddressController(addressService, measurementService, billService, userService);
        addressRepositoryMock = mock(AddressRepository.class);
        addressService = new AddressService(addressRepositoryMock);
        exception = null;
    }

/*  @Test
    public void getById_Status200(){
        //Arrange (preparo variables)
        User user = new User(1, null, null, false, null);
        Integer id = 1;
        Address address = new Address (1, null, null, null, null, null);
        when(auth.getPrincipal()).thenReturn(UserDTO.builder().userName("user").build() ); //me perdi necito a ina
        when(addressController.getById(auth, id)).thenReturn(ResponseEntity.status(HttpStatus.OK).header("null", "null").body(addressService.getById(id)));
        //Assert (testeo que me devolvio toodo ok)
        try{
            Address result = ResponseEntity.body(addressController.getById(auth, id));
            Assertions.assertEquals(address, result);
            Assertions.assertNotNull(result);
        }catch( ResponseStatusException e){
            exception = e;
        }
        //Act (llamo al metodo

    }*/
}
