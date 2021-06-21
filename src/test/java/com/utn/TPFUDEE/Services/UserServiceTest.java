package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Models.User;
import com.utn.TPFUDEE.Repositories.UserRepository;
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
    public void getByIdTest_ReturnUser(){
        Integer id = 1;
        Mockito.when(userRepositoryMock.findById(id)).thenReturn(Optional.of(user));

        User result = userService.getById(id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(user, result);
    }

    @Test
    public void getByIdTest_UserNotFound(){
        Mockito.when(userRepositoryMock.findById(user.getUserId())).thenReturn(Optional.empty());

        Assertions.assertThrows(ResponseStatusException.class, () ->{
            userService.getById(user.getUserId());
        });
    }

    @Test
    public void getByUserNameAndPasswordTest_ReturnUser(){
        Mockito.when(userRepositoryMock.getUserByUserNameAndPassword(user.getUserName(), user.getPassword())).thenReturn(user);

        User result = userService.getUserByUserNameAndPassword(user.getUserName(), user.getPassword());

        Assertions.assertNotNull(result);
        Assertions.assertEquals(user, result);
    }
//Falta retornar user not found
/*    @Test
    public void getByUserNameAndPasswordTest_UserNotFound(){
        Mockito.when(userRepositoryMock.getUserByUserNameAndPassword(user.getUserName(), user.getPassword())).thenReturn(null);

        Assertions.assertThrows(ResponseStatusException.class, () ->{
            userService.getUserByUserNameAndPassword(user.getUserName(), user.getPassword());
        });
    }*/

    @Test
    public void getPageTest(){
        Pageable pageable = PageRequest.of(0, 1);
        List<User> list = new ArrayList<>();
        list.add(user);
        Page<User> userPage = new PageImpl<>(list, pageable, pageable.getPageSize());
        Mockito.when(userRepositoryMock.findAll(pageable)).thenReturn(userPage);

        Page<User> result = userService.getAll(pageable);

        Assertions.assertNotNull(result);
    }

    @Test
    public void getPageTest_NoContent(){
        Pageable pageable = PageRequest.of(0, 1);
        Mockito.when(userRepositoryMock.findAll(pageable)).thenReturn(Page.empty());

        Assertions.assertThrows(ResponseStatusException.class, () ->{
            userService.getAll(pageable);
        });
    }

    @Test
    public void addTest(){
        Mockito.when(userRepositoryMock.save(user)).thenReturn(user);
        Mockito.when(userRepositoryMock.findById(user.getUserId())).thenReturn(Optional.empty());

        User result = userService.add(user);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(user, result);
    }

    @Test
    public void addTest_UserExist(){
        Mockito.when(userRepositoryMock.findByUserName(user.getUserName())).thenReturn(user);

        Assertions.assertThrows(ResponseStatusException.class, () ->{
            userService.add(user);
        });
    }

    @Test
    public void deleteTest(){
        Mockito.when(userRepositoryMock.findById(user.getUserId())).thenReturn(Optional.of(user));

        Integer result = userService.deleteById(user.getUserId());
        Assertions.assertNotNull(result);
    }

}
