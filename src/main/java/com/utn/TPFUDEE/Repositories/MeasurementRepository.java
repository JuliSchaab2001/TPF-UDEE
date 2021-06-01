package com.utn.TPFUDEE.Repositories;

import com.utn.TPFUDEE.Models.Measurement;
import com.utn.TPFUDEE.Models.Projections.MeasurementProjection;
import com.utn.TPFUDEE.Models.Projections.MoneyAndKwProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    Page<Measurement> findAll(Pageable pageable);

    //@Query(value =  "Select measurements_id as id, kw, date FROM measurements WHERE measurements_id = ?1 AND date BETWEEN ?2 AND ?3 ", nativeQuery = true)
    Page<MeasurementProjection> findByMeterAndDateBetween(Integer id, String from, String to, Pageable pageable);

    @Procedure(name = "Name del procedimiento(:id, :from, :to,)") //Germo aca te tenes que lucir padre
    MoneyAndKwProjection getAddressConsumes(Integer id, String from, String to);
}
