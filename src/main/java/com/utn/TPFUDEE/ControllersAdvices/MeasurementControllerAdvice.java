package com.utn.TPFUDEE.ControllersAdvices;

import com.utn.TPFUDEE.Exceptions.ClientExistException;
import com.utn.TPFUDEE.Exceptions.ErrorMessage;
import com.utn.TPFUDEE.Exceptions.MeasurementExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MeasurementControllerAdvice {

    @ExceptionHandler(value = {MeasurementExistException.class})
    public ResponseEntity<ErrorMessage> measurementExist(){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorMessage.builder().code("ME").message("Measurement Already Exist").build());
    }
}
