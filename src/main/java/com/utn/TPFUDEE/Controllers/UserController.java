package com.utn.TPFUDEE.Controllers;

import com.utn.TPFUDEE.Exceptions.Exist.UserExistException;
import com.utn.TPFUDEE.Exceptions.NoContent.UserNoContentException;
import com.utn.TPFUDEE.Exceptions.NotFound.UserNotFoundException;
import com.utn.TPFUDEE.Models.User;
import com.utn.TPFUDEE.Projections.UsersClients;
import com.utn.TPFUDEE.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<User>> getAll(Pageable pageable) throws UserNoContentException {
        Page<User> p = userService.getAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).header("X-Total-Count", Long.toString(p.getTotalElements())).header("X-Total-Pages", Long.toString(p.getTotalPages())).body(p.getContent());
    }

    @GetMapping("/projection")
    public ResponseEntity<List<UsersClients>> getUsersClients(Pageable pageable) throws UserNoContentException{
        Page<UsersClients> p = userService.getUsersClients(pageable);
        return ResponseEntity.status(HttpStatus.OK).header("X-Total-Count", Long.toString(p.getTotalElements())).header("X-Total-Pages", Long.toString(p.getTotalPages())).body(p.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Integer id) throws UserNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).header("Nombre", "Cuerpo").body( userService.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity add(@RequestBody User user) throws UserExistException, UserNoContentException {
        userService.add(user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).header("Cuerpo", "Aca habria que poner un header location").build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) throws UserNotFoundException {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).header("Aca", "Salio todo en orden man").build();
    }
}