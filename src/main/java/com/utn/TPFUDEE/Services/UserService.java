package com.utn.TPFUDEE.Services;


import com.utn.TPFUDEE.Models.User;
import com.utn.TPFUDEE.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> getAll(Pageable pageable) {
        Page<User> userList = userRepository.findAll(pageable);

        if(userList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Empty User List");
        }
        return userList;
    }

    public User add(User user){
        if(userRepository.findByUserName(user.getUserName())!= null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User Already exist, try another userName");
        } else{
            return userRepository.save(user);
        }
    }

    public User getById(Integer id){
        return userRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));
    }

    public void deleteById(Integer id){
        this.getById(id);
        userRepository.deleteById(id);
    }

    public User getUserByUserNameAndPassword(String userName, String password) {
        return userRepository.getUserByUserNameAndPassword(userName, password);
    }
}
