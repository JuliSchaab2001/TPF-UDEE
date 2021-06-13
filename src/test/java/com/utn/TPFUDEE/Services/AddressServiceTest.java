package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Models.Address;
import com.utn.TPFUDEE.Repositories.AddressRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.mock;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class AddressServiceTest {

    private AddressService addressService;
    private AddressRepository addressRepositoryMock;

    @BeforeAll
    public void setUp(){
        addressRepositoryMock = mock(AddressRepository.class);
        addressService = new AddressService(addressRepositoryMock);
    }

    @Test
    public void getById_ReturnAddress(){
        Integer id = 1;
        Address address = new Address(id, null, 1, null, null, null);
        Mockito.when(addressRepositoryMock.findById(id)).thenReturn(Optional.of(address));

        Address result = addressService.getById(id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(address, result);
    }

    @Test
    public void Add_ReturnAddress(){
        Address address = new Address(1, "a", 1, null, null, null);
        Mockito.when(addressRepositoryMock.save(address)).thenReturn(address);

        Address result = addressService.add(address);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(address, result);
    }

}
