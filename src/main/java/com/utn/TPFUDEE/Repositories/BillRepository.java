package com.utn.TPFUDEE.Repositories;

import com.utn.TPFUDEE.Models.Address;
import com.utn.TPFUDEE.Models.Bill;
import com.utn.TPFUDEE.Models.Projections.BillProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BillRepository extends CrudRepository<Bill,Integer> {
    Page<Bill> findAll(Pageable pageable);

    @Query(value= "SELECT \n" +
            "    b.bill_id AS id,\n" +
            "    b.initial_measurement AS initialMeasurement,\n" +
            "    b.final_measurement AS finalMeasurement,\n" +
            "    b.initial_date AS initialDate,\n" +
            "    b.final_date AS finalDate,\n" +
            "    b.consumed_kw AS kw,\n" +
            "    b.total AS total,\n" +
            "    t.type,\n" +
            "    c.name,\n" +
            "    c.last_name,\n" +
            "    a.street,\n" +
            "    a.number\n" +
            "FROM\n" +
            "    addresses a\n" +
            "        INNER JOIN\n" +
            "    clients c ON c.dni = a.dni\n" +
            "        INNER JOIN\n" +
            "    tariffs t ON t.tariff_id = a.tariff_id\n" +
            "        INNER JOIN\n" +
            "    meters m ON m.address_id = a.address_id\n" +
            "        INNER JOIN\n" +
            "    bills b ON b.meter_id = m.meter_id\n" +
            "WHERE\n" +
            "    a.address_id = ?1\n" +
            "        AND b.is_paid = 0", nativeQuery = true)
    List<BillProjection> getUnPaidBillsByAddress(Integer id);

    @Query(value= "SELECT \n" +
            "    b.bill_id AS id,\n" +
            "    b.initial_measurement AS initialMeasurement,\n" +
            "    b.final_measurement AS finalMeasurement,\n" +
            "    b.initial_date AS initialDate,\n" +
            "    b.final_date AS finalDate,\n" +
            "    b.consumed_kw AS kw,\n" +
            "    b.total AS total,\n" +
            "    t.type,\n" +
            "    c.name,\n" +
            "    c.last_name,\n" +
            "    a.street,\n" +
            "    a.number\n" +
            "FROM\n" +
            "    addresses a\n" +
            "        INNER JOIN\n" +
            "    clients c ON c.dni = a.dni\n" +
            "        INNER JOIN\n" +
            "    tariffs t ON t.tariff_id = a.tariff_id\n" +
            "        INNER JOIN\n" +
            "    meters m ON m.address_id = a.address_id\n" +
            "        INNER JOIN\n" +
            "    bills b ON b.meter_id = m.meter_id\n" +
            "WHERE\n" +
            "    c.dni = ?1\n" +
            "        AND b.is_paid = 0", nativeQuery = true)
    List<BillProjection> getUnPaidBillsByClient(Integer id);
}
