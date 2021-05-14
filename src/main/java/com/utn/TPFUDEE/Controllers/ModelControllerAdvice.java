package com.utn.TPFUDEE.Controllers;

import com.utn.TPFUDEE.Exceptions.ErrorMessage;
import com.utn.TPFUDEE.Exceptions.ExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice

// No se si es necesaria esta clase, capaz solo con el @ControllerAdvice es suficiente
public class ModelControllerAdvice {
    @ExceptionHandler(value = {ExistsException.class})
    public ResponseEntity<ErrorMessage> alreadyExists(){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorMessage.builder().code("AE").message("This object already exists").build());
    }
}
