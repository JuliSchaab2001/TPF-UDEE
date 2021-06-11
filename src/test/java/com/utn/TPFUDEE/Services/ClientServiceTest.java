package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Models.Client;
import com.utn.TPFUDEE.Repositories.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.mock;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class ClientServiceTest {

    private ClientService clientService;
    private ClientRepository clientRepositoryMock;

    @BeforeAll
    public void setUp(){
        clientRepositoryMock = mock(ClientRepository.class);
        clientService = new ClientService(clientRepositoryMock);
    }

    @Test
    public void getById_ReturnClient(){
        Integer id = 1;
        Client client = new Client(id, null, null, null, null, null);
        Mockito.when(clientRepositoryMock.findById(id)).thenReturn(Optional.of(client));

        Client result = clientService.getById(id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(client, result);
    }

}
