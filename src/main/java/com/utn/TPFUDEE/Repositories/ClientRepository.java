package com.utn.TPFUDEE.Repositories;

import com.utn.TPFUDEE.Models.Client;
import com.utn.TPFUDEE.Models.Projections.ClientProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Page<Client> findAll(Pageable pageable);

    @Procedure(name = "Name del procedure") //Germo aca te lucis de nuevo papa mandale mecha
    List<ClientProjection> getTopTenMostConsumers();
}
