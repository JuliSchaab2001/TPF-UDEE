package com.utn.TPFUDEE.ControllersAdvices;

import com.utn.TPFUDEE.Exceptions.AddressExistException;
import com.utn.TPFUDEE.Exceptions.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AddressControllerAdvice {

    @ExceptionHandler(value = {AddressExistException.class})
    public ResponseEntity<ErrorMessage> addressExist(){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorMessage.builder().code("AE").message("Address Already Exist").build());
    }
}
