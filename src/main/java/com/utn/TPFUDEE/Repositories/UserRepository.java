package com.utn.TPFUDEE.Repositories;

import com.utn.TPFUDEE.Models.User;
import com.utn.TPFUDEE.Projections.UsersClients;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    Page<User> findAll(Pageable pageable);

    @Query(value = "select u.user_id, u.user_name, c.name, c.last_name, c.mail from users u " +
            "inner join clients c on u.dni = c.dni " +
            "where u.is_employee=0 " +
            "order by u.user_id desc",
            countQuery = "select count(*) from users u " +
                    "inner join clients c on u.dni = c.dni " +
                    "where u.is_employee=0;",
            nativeQuery = true)
    Page<UsersClients> getUsersClients(Pageable pageable);
}
