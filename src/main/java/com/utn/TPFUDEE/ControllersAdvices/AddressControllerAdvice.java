package com.utn.TPFUDEE.ControllersAdvices;

import com.utn.TPFUDEE.Exceptions.Exist.AddressExistException;
import com.utn.TPFUDEE.Exceptions.ErrorMessage;
import com.utn.TPFUDEE.Exceptions.NoContent.AddressNoContentException;
import com.utn.TPFUDEE.Exceptions.NotFound.AddressNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AddressControllerAdvice {

    @ExceptionHandler(value = {AddressExistException.class})
    public ResponseEntity<ErrorMessage> addressExist(){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorMessage.builder().code("AE").message("Address Already Exist").build());
    }

    @ExceptionHandler(value = {AddressNotFoundException.class})
    public ResponseEntity<ErrorMessage> addressNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.builder().code("ANF").message("Address Not Found").build());
    }

    @ExceptionHandler(value= {AddressNoContentException.class})
    public ResponseEntity<ErrorMessage> addressNoContent(){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ErrorMessage.builder().code("ANC").message("hay que devolver una lista aca").build());
    }
}
