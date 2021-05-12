package com.utn.TPFUDEE.Controllers;


import com.utn.TPFUDEE.Models.User;
import com.utn.TPFUDEE.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Integer id){
        return userService.getById(id);
    }

    @PostMapping("/")
    public void add(@RequestBody User user){
        userService.add(user);
    }
}