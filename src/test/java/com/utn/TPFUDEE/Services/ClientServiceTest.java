package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Models.Bill;
import com.utn.TPFUDEE.Models.Client;
import com.utn.TPFUDEE.Repositories.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.mock;


@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class ClientServiceTest {

    @Mock
    private ClientService clientService;
    @Mock
    private ClientRepository clientRepositoryMock;
    private Client client;


    @BeforeAll
    public void setUp(){
        clientRepositoryMock = mock(ClientRepository.class);
        clientService = new ClientService(clientRepositoryMock);
        client = new Client(1, null, null, null, null, null);
    }

    @Test
    public void getById_ReturnClient(){
        Integer id = 1;
        Mockito.when(clientRepositoryMock.findById(id)).thenReturn(Optional.of(client));

        Client result = clientService.getById(id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(client, result);
    }

    @Test
    public void Add_ReturnClient(){
        Mockito.when(clientRepositoryMock.save(client)).thenReturn(client);

        Client result = clientService.add(client);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(client, result);
    }

}
