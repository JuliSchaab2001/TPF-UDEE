package com.utn.TPFUDEE.ControllersAdvices;

import com.utn.TPFUDEE.Exceptions.AddressExistException;
import com.utn.TPFUDEE.Exceptions.BillExistException;
import com.utn.TPFUDEE.Exceptions.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BillControllerAdvice {

    @ExceptionHandler(value = {BillExistException.class})
    public ResponseEntity<ErrorMessage> billExist(){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorMessage.builder().code("BE").message("Bill Already Exist").build());
    }
}
