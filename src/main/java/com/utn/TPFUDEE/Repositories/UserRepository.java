package com.utn.TPFUDEE.Repositories;

import com.utn.TPFUDEE.Models.User;
import com.utn.TPFUDEE.Projections.UsersClients;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    Page<User> findAll(Pageable pageable);

    @Query(value = "SELECT u.user_name, c.name, c.last_name, c.mail FROM users u " +
            "INNER JOIN clients c ON u.dni = c.dni " +
            "WHERE u.is_employee = 0 ORDER BY ?#{#pageable}",
            countQuery = "SELECT count(*) FROM users u " +
                    "INNER JOIN clients c ON u.dni = c.dni " +
                    "WHERE is_employee = 0",
            nativeQuery = true)
    Page<UsersClients> getUsersClients(Pageable pageable);
}
