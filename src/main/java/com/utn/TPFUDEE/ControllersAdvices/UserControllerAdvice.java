package com.utn.TPFUDEE.ControllersAdvices;

import com.utn.TPFUDEE.Exceptions.ClientExistException;
import com.utn.TPFUDEE.Exceptions.ErrorMessage;
import com.utn.TPFUDEE.Exceptions.UserExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(value = {UserExistException.class})
    public ResponseEntity<ErrorMessage> userExist(){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorMessage.builder().code("UE").message("User Already Exist").build());
    }
}
