package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Models.Client;
import com.utn.TPFUDEE.Models.Projections.ClientProjection;
import com.utn.TPFUDEE.Repositories.ClientRepository;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDate.now;
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
        client = new Client(5555, null, null, null, null, null);
    }

    @Test
    public void getByIdTest_ReturnClient(){
        Integer id = 1;
        Mockito.when(clientRepositoryMock.findById(id)).thenReturn(Optional.of(client));

        Client result = clientService.getById(id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(client, result);
    }

    @Test
    public void getByIdTest_ClientNotFound(){
        Mockito.when(clientRepositoryMock.findById(client.getDni())).thenReturn(Optional.empty());

        Assertions.assertThrows(ResponseStatusException.class, () -> clientService.getById(client.getDni()));
    }

    @Test
    public void getPageTest(){
        Pageable pageable = PageRequest.of(0, 1);
        List<Client> list = new ArrayList<>();
        list.add(client);
        Page<Client> clientPage = new PageImpl<>(list, pageable, pageable.getPageSize());
        Mockito.when(clientRepositoryMock.findAll(pageable)).thenReturn(clientPage);

        Page<Client> result = clientService.getAll(pageable);

        Assertions.assertNotNull(result);
    }

    @Test
    public void getPageTest_NoContent(){
        Pageable pageable = PageRequest.of(0, 1);
        Mockito.when(clientRepositoryMock.findAll(pageable)).thenReturn(Page.empty());

        Assertions.assertThrows(ResponseStatusException.class, () -> clientService.getAll(pageable));
    }

    @Test
    public void addTest(){
        Mockito.when(clientRepositoryMock.save(client)).thenReturn(client);
        Mockito.when(clientRepositoryMock.findById(client.getDni())).thenReturn(Optional.empty());

        Client result = clientService.add(client);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(client, result);
    }

    @Test
    public void addTest_ClientExist(){
        Mockito.when(clientRepositoryMock.findById(client.getDni())).thenReturn(Optional.of(client));

        Assertions.assertThrows(ResponseStatusException.class, () -> clientService.add(client));
    }

    @Test
    public void deleteTest(){
        Mockito.when(clientRepositoryMock.findById(client.getDni())).thenReturn(Optional.of(client));

        Integer result = clientService.deleteById(client.getDni());
        Assertions.assertNotNull(result);
    }

    @Test
    public void getTopTenMostConsumersTest_ReturnClientList(){
        List<ClientProjection> list = new ArrayList<>();
        Mockito.when(clientRepositoryMock.getTopTenMostConsumers(LocalDateTime.now(), LocalDateTime.now())).thenReturn(list);

        List<ClientProjection> result = clientService.getTopTenMostConsumers(now(), now());

        Assertions.assertNotNull(result);
        Assertions.assertEquals(list, result);
    }

}
