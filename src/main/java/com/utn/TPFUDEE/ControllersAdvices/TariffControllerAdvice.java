package com.utn.TPFUDEE.ControllersAdvices;

import com.utn.TPFUDEE.Exceptions.ClientExistException;
import com.utn.TPFUDEE.Exceptions.ErrorMessage;
import com.utn.TPFUDEE.Exceptions.TariffExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TariffControllerAdvice {

    @ExceptionHandler(value = {TariffExistException.class})
    public ResponseEntity<ErrorMessage> tariffExist(){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorMessage.builder().code("TE").message("Tariff Already Exist").build());
    }
}
