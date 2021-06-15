package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Models.Address;
import com.utn.TPFUDEE.Repositories.AddressRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class AddressServiceTest {

    private AddressService addressService;
    private AddressRepository addressRepositoryMock;
    private Address address;


    @BeforeAll
    public void setUp(){
        addressRepositoryMock = mock(AddressRepository.class);
        addressService = new AddressService(addressRepositoryMock);
        address = new Address(1, null, 1, null, null, null);

    }

    @Test
    public void getByIdTest_ReturnAddress(){
        Integer id = 1;
        Mockito.when(addressRepositoryMock.findById(id)).thenReturn(Optional.of(address));

        Address result = addressService.getById(id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(address, result);
    }

    @Test
    public void getByIdTest_AddressNotFound(){
        Mockito.when(addressRepositoryMock.findById(address.getAddressId())).thenReturn(Optional.empty());

        Assertions.assertThrows(ResponseStatusException.class, () ->{
            addressService.getById(address.getAddressId());
        });
    }

    @Test
    public void getPageTest(){
        Pageable pageable = PageRequest.of(0, 1);
        List<Address> list = new ArrayList<>();
        list.add(address);
        Page<Address> addressPage = new PageImpl<>(list, pageable, pageable.getPageSize());
        Mockito.when(addressRepositoryMock.findAll(pageable)).thenReturn(addressPage);

        Page<Address> result = addressService.getAll(pageable);

        Assertions.assertNotNull(result);
    }

    @Test
    public void getPage_AddressNotFound(){
        Pageable pageable = PageRequest.of(0, 1);
        Mockito.when(addressRepositoryMock.findAll(pageable)).thenReturn(Page.empty());

        Assertions.assertThrows(ResponseStatusException.class, () ->{
            addressService.getAll(pageable);
        });
    }

    @Test
    public void addTest(){
        Mockito.when(addressRepositoryMock.save(address)).thenReturn(address);
        Mockito.when(addressRepositoryMock.findByStreetAndNumber(address.getStreet(), address.getNumber())).thenReturn(null);

        Address result = addressService.add(address);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(address, result);
    }

    @Test
    public void addTest_AddressExist(){
        Mockito.when(addressRepositoryMock.findByStreetAndNumber(address.getStreet(), address.getNumber())).thenReturn(address);

        Assertions.assertThrows(ResponseStatusException.class, () ->{
            addressService.add(address);
        });
    }

    @Test
    public void updateTest(){
        Mockito.when(addressRepositoryMock.findById(address.getAddressId())).thenReturn(Optional.of(address));
        Mockito.when(addressRepositoryMock.save(address)).thenReturn(address);

        Address result = addressService.update(address);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(address, result);
    }

    @Test
    public void updateTest_AddressNotFound(){
        Mockito.when(addressRepositoryMock.findById(address.getAddressId())).thenReturn(Optional.empty());

        Assertions.assertThrows(ResponseStatusException.class, () ->{
            addressService.getById(address.getAddressId());
        });
    }
}
