package com.utn.TPFUDEE.Controllers;

import com.utn.TPFUDEE.Exceptions.Exist.UserExistException;
import com.utn.TPFUDEE.Exceptions.NoContent.UserNoContentException;
import com.utn.TPFUDEE.Exceptions.NotFound.UserNotFoundException;
import com.utn.TPFUDEE.Models.User;
import com.utn.TPFUDEE.Services.UserService;
import com.utn.TPFUDEE.Utils.EntityURLBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final String USER_PATH = "user";

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<User>> getAll(Pageable pageable) throws UserNoContentException {
        Page<User> p = userService.getAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).header("X-Total-Count", Long.toString(p.getTotalElements())).header("X-Total-Pages", Long.toString(p.getTotalPages())).body(p.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Integer id) throws UserNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).header("Nombre", "Cuerpo").body( userService.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity add(@RequestBody User user) throws UserExistException, UserNoContentException {
        return ResponseEntity.status(HttpStatus.CREATED).location(EntityURLBuilder.buildURL(USER_PATH,userService.add(user).getUser_id())).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) throws UserNotFoundException {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).header("Aca", "Salio todo en orden man").build();
    }
}