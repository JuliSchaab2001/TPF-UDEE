package com.utn.TPFUDEE.Controllers;



import com.utn.TPFUDEE.Exceptions.UserExistException;
import com.utn.TPFUDEE.Models.User;
import com.utn.TPFUDEE.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).header("Nombre", "Cuerpo").body( userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).header("Nombre", "Cuerpo").body( userService.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity add(@RequestBody User user) throws UserExistException {
        userService.add(user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).header("Cuerpo", "Aca habria que poner un header location").build();
    }
}