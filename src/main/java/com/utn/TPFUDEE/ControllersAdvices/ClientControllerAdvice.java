package com.utn.TPFUDEE.ControllersAdvices;

import com.utn.TPFUDEE.Exceptions.AddressExistException;
import com.utn.TPFUDEE.Exceptions.ClientExistException;
import com.utn.TPFUDEE.Exceptions.ErrorMessage;
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
}
