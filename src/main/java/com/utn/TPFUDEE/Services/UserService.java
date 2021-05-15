package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Exceptions.UserExistException;
import com.utn.TPFUDEE.Exceptions.UserNoContentException;
import com.utn.TPFUDEE.Exceptions.UserNotFoundException;
import com.utn.TPFUDEE.Models.User;
import com.utn.TPFUDEE.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() throws UserNoContentException {
        List<User> userList = userRepository.findAll();

        if(userList.isEmpty()) {
            System.out.println(userList);
            throw new UserNoContentException();
        }
        return userList;
    }

    public void add(User user) throws UserExistException {
        boolean flag = false;
        for(User var : this.userRepository.findAll()){
            if(var.getUserName().equals(user.getUserName())){
                flag = true;
            }
        }
        if(flag){
            throw new UserExistException();
        }else{
            userRepository.save(user);
        }
    }

    public User getById(Integer id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException());
    }

}
