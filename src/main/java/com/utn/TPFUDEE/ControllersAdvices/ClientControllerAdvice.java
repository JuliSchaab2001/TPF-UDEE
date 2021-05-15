package com.utn.TPFUDEE.ControllersAdvices;

import com.utn.TPFUDEE.Exceptions.Exist.ClientExistException;
import com.utn.TPFUDEE.Exceptions.ErrorMessage;
import com.utn.TPFUDEE.Exceptions.NoContent.ClientNoContentException;
import com.utn.TPFUDEE.Exceptions.NotFound.ClientNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ClientControllerAdvice {

    @ExceptionHandler(value = {ClientExistException.class})
    public ResponseEntity<ErrorMessage> clientExist(){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorMessage.builder().code("CE").message("Client Already Exist").build());
    }

    @ExceptionHandler(value= {ClientNotFoundException.class})
    public ResponseEntity<ErrorMessage> clientNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.builder().code("CNF").message("Client Not Found").build());
    }

    @ExceptionHandler(value ={ClientNoContentException.class})
    public ResponseEntity<ErrorMessage> clientNoContent(){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ErrorMessage.builder().code("CNC").message("Client No Content").build());
    }

}
