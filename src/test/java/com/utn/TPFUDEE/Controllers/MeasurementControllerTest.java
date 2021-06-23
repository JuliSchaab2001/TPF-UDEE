package com.utn.TPFUDEE.Controllers;

import com.utn.TPFUDEE.Models.DTO.UserDTO;
import com.utn.TPFUDEE.Models.Tariff;
import com.utn.TPFUDEE.Models.User;
import com.utn.TPFUDEE.Services.*;
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
public class TariffControllerTest {

    private Authentication auth;
    private TariffController tariffController;
    private TariffService tariffService;
    private UserService userServiceMock;
    private Tariff tariff;
    User user;


    @BeforeAll
    public void setUp(){
        auth = mock(Authentication.class);
        tariffService = mock(TariffService.class);
        userServiceMock = mock(UserService.class);
        tariffController = new TariffController(tariffService, userServiceMock);
        user = new User(1, null, null, true, null);
        tariff = new Tariff(1, null, null, null);
    }

    @Test
    public void addTest_StatusCreated(){
        Mockito.when(tariffService.add(tariff)).thenReturn(tariff);
        validateRol_IsEmployee();

        try(MockedStatic<EntityURLBuilder> entityURLBuilderMockedStatic = Mockito.mockStatic(EntityURLBuilder.class)){
            entityURLBuilderMockedStatic.when(() -> EntityURLBuilder.buildURL(TariffController.TARIFF_PATH, tariff.getTariffId())).thenReturn(URI.create("a"));

            ResponseEntity result = tariffController.add(auth, tariff);

            Assertions.assertEquals(HttpStatus.CREATED.value(), result.getStatusCodeValue());
        }
    }

    @Test
    public void addTest_StatusForbidden(){
        validateRol_IsClient();

        Assertions.assertThrows(ResponseStatusException.class, () -> tariffController.add(auth, tariff));
    }

    @Test
    public void deleteByIdTest_StatusCreated(){
        Mockito.when(tariffService.deleteById(tariff.getTariffId())).thenReturn(tariff.getTariffId());
        validateRol_IsEmployee();

        ResponseEntity result = tariffController.deleteById(auth, tariff.getTariffId());

        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
    }

    @Test
    public void deleteByIdTest_StatusForbidden(){
        validateRol_IsClient();

        Assertions.assertThrows(ResponseStatusException.class, () -> tariffController.deleteById(auth, 1));
    }

    @Test
    public void updateTest_StatusOk(){
        Mockito.when(tariffService.update(tariff)).thenReturn(tariff);
        validateRol_IsEmployee();

        try(MockedStatic<EntityURLBuilder> entityURLBuilderMockedStatic = Mockito.mockStatic(EntityURLBuilder.class)){
            entityURLBuilderMockedStatic.when(() -> EntityURLBuilder.buildURL(TariffController.TARIFF_PATH, tariff.getTariffId())).thenReturn(URI.create("a"));

            ResponseEntity result = tariffController.update(auth, tariff);

            Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
        }
    }

    @Test
    public void updateTest_StatusForbidden(){
        validateRol_IsClient();

        Assertions.assertThrows(ResponseStatusException.class, () -> tariffController.update(auth, tariff));
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
