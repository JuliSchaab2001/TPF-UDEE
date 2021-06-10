package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Models.Address;
import com.utn.TPFUDEE.Models.Projections.AddressProjection;
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
        //Arrange
        Integer id = 1;
        Address address = new Address(id, null, 1, null, null, null);
        Mockito.when(addressRepositoryMock.findById(id)).thenReturn(Optional.of(address));
        //Act
        Address result = addressService.getById(id);
        //Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(address, result);
    }

}
