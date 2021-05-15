package com.utn.TPFUDEE.ControllersAdvices;

import com.utn.TPFUDEE.Exceptions.Exist.BillExistException;
import com.utn.TPFUDEE.Exceptions.ErrorMessage;
import com.utn.TPFUDEE.Exceptions.NoContent.BillNoContentException;
import com.utn.TPFUDEE.Exceptions.NotFound.BillNotFoundException;
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

    @ExceptionHandler(value = {BillNotFoundException.class})
    public ResponseEntity<ErrorMessage> billNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.builder().code("BNF").message("Bill not found").build());
    }

    @ExceptionHandler(value = {BillNoContentException.class})
    public ResponseEntity<ErrorMessage> billNoContent(){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ErrorMessage.builder().code("BNC").message("Devolver lista vacia no se como").build());
    }
}
