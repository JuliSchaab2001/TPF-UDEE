package com.utn.TPFUDEE.Repositories;

import com.utn.TPFUDEE.Models.Meter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface MeterRepository extends CrudRepository<Meter, Integer> {
    Page<Meter> findAll(Pageable pageable);
}
