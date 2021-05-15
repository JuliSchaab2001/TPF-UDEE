package com.utn.TPFUDEE.ControllersAdvices;

import com.utn.TPFUDEE.Exceptions.ErrorMessage;
import com.utn.TPFUDEE.Exceptions.Exist.MeasurementExistException;
import com.utn.TPFUDEE.Exceptions.NoContent.MeasurementNoContentException;
import com.utn.TPFUDEE.Exceptions.NotFound.MeasurementNotFoundException;
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

    @ExceptionHandler(value = {MeasurementNotFoundException.class})
    public ResponseEntity<ErrorMessage> measurementNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.builder().code("MNF").message("Measurement Not Found").build());
    }

    @ExceptionHandler(value = {MeasurementNoContentException.class})
    public ResponseEntity<ErrorMessage> measurementNoontent(){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ErrorMessage.builder().code("MNC").message("Devolver una lista vacia aca").build());
    }
}
