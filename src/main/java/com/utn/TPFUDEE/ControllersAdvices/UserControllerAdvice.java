package com.utn.TPFUDEE.ControllersAdvices;

import com.utn.TPFUDEE.Exceptions.ErrorMessage;
import com.utn.TPFUDEE.Exceptions.UserExistException;
import com.utn.TPFUDEE.Exceptions.UserNoContentException;
import com.utn.TPFUDEE.Exceptions.UserNotFoundException;
import com.utn.TPFUDEE.Models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(value = {UserExistException.class})
    public ResponseEntity<ErrorMessage> userExist(){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorMessage.builder().code("UE").message("User Already Exist").build());
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<ErrorMessage> notFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.builder().code("UNF").message("User Not Found").build());
    }

    @ExceptionHandler(value = {UserNoContentException.class})
    public ResponseEntity<ErrorMessage> noContent(){
        List<User> list = new ArrayList<>();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ErrorMessage.builder().code("LVCIA").message("Deberia devolver lista vacia no se que pasa").build());
    }
}
