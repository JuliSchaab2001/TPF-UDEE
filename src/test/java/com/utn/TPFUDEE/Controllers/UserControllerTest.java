package com.utn.TPFUDEE.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utn.TPFUDEE.Models.User;
import com.utn.TPFUDEE.Repositories.UserRepository;
import com.utn.TPFUDEE.Services.UserService;
import com.utn.TPFUDEE.Utils.EntityURLBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class UserControllerTest {

    private UserController userController;
    private UserService userService;
    User user;
    ObjectMapper objectMapper;
    ModelMapper modelMapper;


    /*@BeforeAll
    public void setUp(){
        objectMapper = mock(ObjectMapper.class);
        modelMapper = mock(ModelMapper.class);
        userService = mock(UserService.class);
        userController = new UserController(userService, objectMapper, modelMapper);
        user = new User(1, null, null, true, null);
    }

    @Test
    public void addTest_StatusCreated(){
        Mockito.when(userService.add(user)).thenReturn(user);

        try(MockedStatic<EntityURLBuilder> entityURLBuilderMockedStatic = Mockito.mockStatic(EntityURLBuilder.class)){
            entityURLBuilderMockedStatic.when(() -> EntityURLBuilder.buildURL(UserController.USER_PATH, user.getUserId())).thenReturn(URI.create("a"));

            ResponseEntity result = userController.add(user);

            Assertions.assertEquals(HttpStatus.CREATED.value(), result.getStatusCodeValue());
        }
    }

    @Test
    public void getAllTest_StatusOk(){
        Pageable pageable = PageRequest.of(0, 1);
        List<User> list = new ArrayList<>();
        list.add(user);
        Page<User> userPage = new PageImpl<>(list, pageable, pageable.getPageSize());
        Mockito.when(userService.getAll(pageable)).thenReturn(userPage);

        ResponseEntity<List<User>> result = userController.getAll(0, 1);

        Assertions.assertNotNull(result);
    }

    @Test
    public void getByIdTest_StatusOk(){
        Integer id = 1;
        Mockito.when(userService.getById(id)).thenReturn(user);

        ResponseEntity result = userController.getById(id);

        Assertions.assertEquals(user, result.getBody());
        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
    }


    @Test
    public void deleteByIdTest_StatusCreated(){
        Mockito.when(userService.deleteById(user.getUserId())).thenReturn(user.getUserId());

        ResponseEntity result = userController.deleteById(user.getUserId());

        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
    }*/
}
