package com.utn.TPFUDEE.Controllers;

import com.utn.TPFUDEE.Models.Projections.AddressProjection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class AddressControllerTest {
    @Test
    public void getById_Status200(){
        //Arrange (preparo variables)
        Integer id = 1;
        //Act (llamo al metodo)
        //ResponseEntity<AddressProjection> result =;
        //Assert (testeo que me devolvio toodo ok)



    }
}
