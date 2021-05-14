package com.utn.TPFUDEE.ControllersAdvices;

import com.utn.TPFUDEE.Exceptions.ClientExistException;
import com.utn.TPFUDEE.Exceptions.ErrorMessage;
import com.utn.TPFUDEE.Exceptions.MeterTypeExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MeterTypeControllerAdvice {

    @ExceptionHandler(value = {MeterTypeExistException.class})
    public ResponseEntity<ErrorMessage> meterTypeExist(){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorMessage.builder().code("MTE").message("Meter Type Already Exist").build());
    }
}
