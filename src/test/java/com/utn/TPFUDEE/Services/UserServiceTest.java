package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Models.MeterType;
import com.utn.TPFUDEE.Models.User;
import com.utn.TPFUDEE.Repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.mock;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {

    private UserService userService;
    private UserRepository userRepositoryMock;
    private User user;


    @BeforeAll
    public void setUp(){
        userRepositoryMock = mock(UserRepository.class);
        userService = new UserService(userRepositoryMock);
        user = new User(1, null, null, false, null);

    }

    @Test
    public void getById_ReturnUser(){
        Integer id = 1;
        Mockito.when(userRepositoryMock.findById(id)).thenReturn(Optional.of(user));

        User result = userService.getById(id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(user, result);
    }

    @Test
    public void Add_ReturnUser(){
        Mockito.when(userRepositoryMock.save(user)).thenReturn(user);

        User result = userService.add(user);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(user, result);
    }

}
