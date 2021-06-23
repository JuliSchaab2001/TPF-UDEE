package com.utn.TPFUDEE.Controllers;

import com.utn.TPFUDEE.Models.Client;
import com.utn.TPFUDEE.Models.DTO.UserDTO;
import com.utn.TPFUDEE.Models.Projections.ClientProjection;
import com.utn.TPFUDEE.Models.User;
import com.utn.TPFUDEE.Services.*;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class ClientControllerTest {

    private Authentication auth;
    private ClientController clientController;
    private ClientService clientService;
    @Mock
    private BillService billService;
    private UserService userServiceMock;
    private Client client;
    User user;


    @BeforeAll
    public void setUp(){
        auth = mock(Authentication.class);
        clientService = mock(ClientService.class);
        userServiceMock = mock(UserService.class);
        clientController = new ClientController(clientService, billService, userServiceMock);
        user = new User(1, null, null, true, client);
        client = new Client(1, null, null, null, null, user);
    }

    @Test
    public void addTest_StatusCreated(){
        Mockito.when(clientService.add(client)).thenReturn(client);

        try(MockedStatic<EntityURLBuilder> entityURLBuilderMockedStatic = Mockito.mockStatic(EntityURLBuilder.class)){
            entityURLBuilderMockedStatic.when(() -> EntityURLBuilder.buildURL(ClientController.CLIENT_PATH, client.getDni())).thenReturn(URI.create("a"));

            ResponseEntity result = clientController.add(client);

            Assertions.assertEquals(HttpStatus.CREATED.value(), result.getStatusCodeValue());
        }
    }

    @Test
    public void getByIdTest_StatusOk(){
        Integer id = 1;
        validate_IsEmployee();
        Mockito.when(clientService.getById(id)).thenReturn(client);

        ResponseEntity result = clientController.getById(auth, id);

        Assertions.assertEquals(client, result.getBody());
        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
    }

    @Test
    public void getByIdTest_StatusForbidden(){
        validate_IsClient();

        Assertions.assertThrows(ResponseStatusException.class, () -> clientController.getById(auth, 4));
    }

    @Test
    public void deleteByIdTest_StatusCreated(){
        Mockito.when(clientService.deleteById(client.getDni())).thenReturn(client.getDni());
        validateRol_IsEmployee();

        ResponseEntity result = clientController.deleteById(auth, client.getDni());

        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
    }

    @Test
    public void deleteByIdTest_StatusForbidden(){
        validateRol_IsClient();

        Assertions.assertThrows(ResponseStatusException.class, () -> clientController.deleteById(auth, 1));
    }

    @Test
    public void getTopTenMostConsumersTest_StatusOk(){
        List<ClientProjection> list = new ArrayList<>();
        LocalDate date = LocalDate.now();
        validate_IsEmployee();
        Mockito.when(clientService.getTopTenMostConsumers(date, date)).thenReturn(list);

        ResponseEntity<List<ClientProjection>> result = clientController.getTopTenMostConsumers(auth, date.toString(), date.toString());

        Assertions.assertEquals(list, result.getBody());
        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
    }

    @Test
    public void getTopTenMostConsumersTest_StatusForbidden(){
        validate_IsClient();
        LocalDate date = LocalDate.now();

        Assertions.assertThrows(ResponseStatusException.class, () -> clientController.getTopTenMostConsumers(auth, date.toString(), date.toString()));
    }

    private void validate_IsEmployee(){
        Mockito.when(auth.getPrincipal()).thenReturn(UserDTO.builder().userId(user.getUserId()).build());
        Mockito.when(userServiceMock.getById(user.getUserId())).thenReturn(user);
    }

    private void validate_IsClient(){
        Integer id = 1;
        User user = new User(2, null, null, false, client);
        Mockito.when(auth.getPrincipal()).thenReturn(UserDTO.builder().userId(user.getUserId()).build());
        Mockito.when(userServiceMock.getById(user.getUserId())).thenReturn(user);
        Mockito.when(clientService.getById(id)).thenReturn(client);

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
