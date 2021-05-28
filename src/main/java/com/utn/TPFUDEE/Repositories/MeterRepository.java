package com.utn.TPFUDEE.Repositories;

import com.utn.TPFUDEE.Models.Meter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MeterRepository extends JpaRepository<Meter, Integer> {
    Page<Meter> findAll(Pageable pageable);

    Meter findBySerialNumber(String serialNumber); //deberia de funcionar solo, checkear
}
