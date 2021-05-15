package com.utn.TPFUDEE.ControllersAdvices;

import com.utn.TPFUDEE.Exceptions.*;
import com.utn.TPFUDEE.Models.Meter;
import com.utn.TPFUDEE.Models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class MeterControllerAdvice {

    @ExceptionHandler(value = {MeterExistException.class})
    public ResponseEntity<ErrorMessage> meterExist(){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorMessage.builder().code("MeterE").message("Meter Already Exist").build());
    }

    @ExceptionHandler(value = {MeterNotFoundException.class})
    public ResponseEntity<ErrorMessage> notFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.builder().code("MNF").message("Meter Not Found").build());
    }

    @ExceptionHandler(value = {MeterNoContentException.class})
    public ResponseEntity<ErrorMessage> noContent(){
        List<Meter> list = new ArrayList<>();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ErrorMessage.builder().code("LVCIA").message("Deberia devolver lista vacia no se que pasa").build());
    }
}
