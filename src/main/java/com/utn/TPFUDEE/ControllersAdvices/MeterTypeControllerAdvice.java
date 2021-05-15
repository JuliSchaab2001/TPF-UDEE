package com.utn.TPFUDEE.ControllersAdvices;

import com.utn.TPFUDEE.Exceptions.*;
import com.utn.TPFUDEE.Exceptions.Exist.MeterTypeExistException;
import com.utn.TPFUDEE.Exceptions.NoContent.MeterTypeNoContentException;
import com.utn.TPFUDEE.Exceptions.NotFound.MeterTypeNotFoundException;
import com.utn.TPFUDEE.Models.MeterType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class MeterTypeControllerAdvice {

    @ExceptionHandler(value = {MeterTypeExistException.class})
    public ResponseEntity<ErrorMessage> meterTypeExist(){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorMessage.builder().code("MTE").message("Meter Type Already Exist").build());
    }

    @ExceptionHandler(value = {MeterTypeNotFoundException.class})
    public ResponseEntity<ErrorMessage> notFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.builder().code("MTNF").message("MeterType Not Found").build());
    }

    @ExceptionHandler(value = {MeterTypeNoContentException.class})
    public ResponseEntity<ErrorMessage> noContent(){
        List<MeterType> list = new ArrayList<>();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ErrorMessage.builder().code("LVCIA").message("Deberia devolver lista vacia no se que pasa").build());
    }
}
