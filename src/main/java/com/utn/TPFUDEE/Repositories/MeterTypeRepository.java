package com.utn.TPFUDEE.Repositories;

import com.utn.TPFUDEE.Models.MeterType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeterTypeRepository extends JpaRepository<MeterType, Integer> {
    Page<MeterType> findAll(Pageable pageable);

    MeterType findByModelAndBrand(String model, String brand);
}
