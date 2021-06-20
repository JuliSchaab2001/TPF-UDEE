package com.utn.TPFUDEE.Repositories;

import com.utn.TPFUDEE.Models.Measurement;
import com.utn.TPFUDEE.Models.Projections.MeasurementProjection;
import com.utn.TPFUDEE.Models.Projections.MoneyAndKwProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    Page<Measurement> findAll(Pageable pageable);

    @Query(
            value = "SELECT M FROM Measurement M WHERE M.meter.meterId = ?1 and M.date BETWEEN ?2 and ?3"
    )
    Page<MeasurementProjection> findByMeterIdAndDateBetween(Integer id, String from, String to, Pageable pageable);//Tengo problemas con los formatos de las fechas

    @Query(value = "call get_consumes(?1,?2,?3);", nativeQuery = true)
    MoneyAndKwProjection getAddressConsumes(Integer id, LocalDateTime from, LocalDateTime to);
}
