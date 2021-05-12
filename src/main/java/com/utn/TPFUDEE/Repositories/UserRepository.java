package com.utn.TPFUDEE.Repositories;

import com.utn.TPFUDEE.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
