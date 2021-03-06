package com.utn.TPFUDEE.Repositories;

import com.utn.TPFUDEE.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Page<User> findAll(Pageable pageable);

    User getUserByUserNameAndPassword(String userName, String password);

    User findByUserName(String userName);
}
