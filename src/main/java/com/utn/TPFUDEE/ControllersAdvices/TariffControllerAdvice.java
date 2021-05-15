package com.utn.TPFUDEE.ControllersAdvices;

import com.utn.TPFUDEE.Exceptions.*;
import com.utn.TPFUDEE.Exceptions.Exist.TariffExistException;
import com.utn.TPFUDEE.Exceptions.NoContent.TariffNoContentException;
import com.utn.TPFUDEE.Exceptions.NotFound.TariffNotFoundException;
import com.utn.TPFUDEE.Models.Tariff;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class TariffControllerAdvice {

    @ExceptionHandler(value = {TariffExistException.class})
    public ResponseEntity<ErrorMessage> tariffExist(){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorMessage.builder().code("TE").message("Tariff Already Exist").build());
    }

    @ExceptionHandler(value = {TariffNotFoundException.class})
    public ResponseEntity<ErrorMessage> notFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.builder().code("TNF").message("Tariff Not Found").build());
    }

    @ExceptionHandler(value = {TariffNoContentException.class})
    public ResponseEntity<ErrorMessage> noContent(){
        List<Tariff> list = new ArrayList<>();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ErrorMessage.builder().code("LVCIA").message("Deberia devolver lista vacia no se que pasa").build());
    }
}
