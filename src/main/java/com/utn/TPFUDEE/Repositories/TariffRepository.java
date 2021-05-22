package com.utn.TPFUDEE.Repositories;

import com.utn.TPFUDEE.Models.Tariff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface TariffRepository extends CrudRepository<Tariff, Integer> {
    Page<Tariff> findAll(Pageable pageable);
}
