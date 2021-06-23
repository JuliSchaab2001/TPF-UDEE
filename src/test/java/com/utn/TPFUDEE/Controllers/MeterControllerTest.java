package com.utn.TPFUDEE.Controllers;

import com.utn.TPFUDEE.Models.DTO.UserDTO;
import com.utn.TPFUDEE.Models.Meter;
import com.utn.TPFUDEE.Models.User;
import com.utn.TPFUDEE.Services.MeterService;
import com.utn.TPFUDEE.Services.UserService;
import com.utn.TPFUDEE.Utils.EntityURLBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;

import static org.mockito.Mockito.mock;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class MeterControllerTest {

    private Authentication auth;
    private MeterController meterController;
    private MeterService meterService;
    private UserService userServiceMock;
    private Meter meter;
    User user;

    @BeforeAll
    public void setUp(){
        auth = mock(Authentication.class);
        meterService = mock(MeterService.class);
        userServiceMock = mock(UserService.class);
        meterController = new MeterController(meterService, userServiceMock);
        user = new User(1, null, null, true, null);
        meter = new Meter(1, null, null, null, null, null, null);
    }

    @Test
    public void addTest_StatusCreated(){
        Mockito.when(meterService.add(meter)).thenReturn(meter);
        validateRol_IsEmployee();

        try(MockedStatic<EntityURLBuilder> entityURLBuilderMockedStatic = Mockito.mockStatic(EntityURLBuilder.class)){
            entityURLBuilderMockedStatic.when(() -> EntityURLBuilder.buildURL(MeterController.METER_PATH, meter.getMeterId())).thenReturn(URI.create("a"));

            ResponseEntity result = meterController.add(auth, meter);

            Assertions.assertEquals(HttpStatus.CREATED.value(), result.getStatusCodeValue());
        }
    }

    @Test
    public void addTest_StatusForbidden(){
        validateRol_IsClient();

        Assertions.assertThrows(ResponseStatusException.class, () -> meterController.add(auth, meter));
    }

    @Test
    public void deleteByIdTest_StatusCreated(){
        Mockito.when(meterService.deleteById(meter.getMeterId())).thenReturn(meter.getMeterId());
        validateRol_IsEmployee();

        ResponseEntity result = meterController.deleteById(auth, meter.getMeterId());

        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
    }

    @Test
    public void deleteByIdTest_StatusForbidden(){
        validateRol_IsClient();

        Assertions.assertThrows(ResponseStatusException.class, () -> meterController.deleteById(auth, 1));
    }

    @Test
    public void updateTest_StatusOk(){
        Mockito.when(meterService.update(meter)).thenReturn(meter);
        validateRol_IsEmployee();

        try(MockedStatic<EntityURLBuilder> entityURLBuilderMockedStatic = Mockito.mockStatic(EntityURLBuilder.class)){
            entityURLBuilderMockedStatic.when(() -> EntityURLBuilder.buildURL(MeterController.METER_PATH, meter.getMeterId())).thenReturn(URI.create("a"));

            ResponseEntity result = meterController.update(auth, meter);

            Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
        }
    }

    @Test
    public void updateTest_StatusForbidden(){
        validateRol_IsClient();

        Assertions.assertThrows(ResponseStatusException.class, () -> meterController.update(auth, meter));
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
