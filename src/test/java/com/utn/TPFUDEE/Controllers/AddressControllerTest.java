package com.utn.TPFUDEE.Controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.security.core.Authentication;

import static org.mockito.Mockito.mock;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class AddressControllerTest {

    private Authentication auth;

    @BeforeAll
    public void setUp(){
        auth = mock(Authentication.class);
    }

    @Test
    public void getById_Status200(){
        //Arrange (preparo variables)

        //Act (llamo al metodo)
        //ResponseEntity<AddressProjection> result =;
        //Assert (testeo que me devolvio toodo ok)



    }
}
