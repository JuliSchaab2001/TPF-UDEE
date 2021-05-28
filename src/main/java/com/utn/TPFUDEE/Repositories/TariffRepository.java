package com.utn.TPFUDEE.Repositories;

import com.utn.TPFUDEE.Models.Tariff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TariffRepository extends JpaRepository<Tariff, Integer> {
    Page<Tariff> findAll(Pageable pageable);
}
