package com.utn.TPFUDEE.Repositories;

import com.utn.TPFUDEE.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    //Prueba con pageable
    List<User> findAll(Pageable pageable);
}
