package com.utn.TPFUDEE.Repositories;

import com.utn.TPFUDEE.Models.Client;
import com.utn.TPFUDEE.Models.Projections.ClientProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Page<Client> findAll(Pageable pageable);

    @Query(value = "call top_ten_v1(?1, ?2);", nativeQuery = true)
    List<ClientProjection> getTopTenMostConsumers(LocalDateTime from, LocalDateTime to);
}
