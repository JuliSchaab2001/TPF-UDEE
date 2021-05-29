package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Exceptions.Exist.UserExistException;
import com.utn.TPFUDEE.Exceptions.NoContent.UserNoContentException;
import com.utn.TPFUDEE.Exceptions.NotFound.UserNotFoundException;
import com.utn.TPFUDEE.Models.User;
import com.utn.TPFUDEE.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> getAll(Pageable pageable) throws UserNoContentException {
        Page<User> userList = userRepository.findAll(pageable);

        if(userList.isEmpty()) {
            throw new UserNoContentException();
        }
        return userList;
    }

    public User add(User user) throws UserExistException {
        boolean flag = false;
        for(User var : this.userRepository.findAll()){
            if(var.getUserName().equals(user.getUserName())){
                flag = true;
            }
        }
        if(flag){
            throw new UserExistException();
        }else{
            return userRepository.save(user);
        }
    }

    public User getById(Integer id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException());
    }

    public void deleteById(Integer id) throws UserNotFoundException {
        this.getById(id);
        userRepository.deleteById(id);
    }

    public User getUserByUserNameAndPassword(String userName, String password) {
        return userRepository.getUserByUserNameAndPassword(userName, password);
    }
}
