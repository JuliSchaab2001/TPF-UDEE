package com.utn.TPFUDEE.ControllersAdvices;

import com.utn.TPFUDEE.Exceptions.ClientExistException;
import com.utn.TPFUDEE.Exceptions.ErrorMessage;
import com.utn.TPFUDEE.Exceptions.MeterExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MeterControllerAdvice {

    @ExceptionHandler(value = {MeterExistException.class})
    public ResponseEntity<ErrorMessage> meterExist(){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorMessage.builder().code("MeterE").message("Meter Already Exist").build());
    }
}
