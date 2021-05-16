package com.utn.TPFUDEE.Repositories;

import com.utn.TPFUDEE.Models.MeterType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface MeterTypeRepository extends CrudRepository<MeterType, Integer> {
    Page<MeterType> findAll(Pageable pageable);
}
