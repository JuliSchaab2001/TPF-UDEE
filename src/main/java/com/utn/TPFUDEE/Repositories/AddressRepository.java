package com.utn.TPFUDEE.Repositories;

import com.utn.TPFUDEE.Models.Address;
import com.utn.TPFUDEE.Models.Projections.addressProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    Page<Address> findAll(Pageable pageable);

    Address findByStreetAndNumber(String street, Integer number);

    @Query(value = "Select a.address_id as id, a.street as street, a.number as number, c.name as name, c.last_name as lastName " +
            "from addresses a INNER JOIN clients c on a.dni= c.dni WHERE address_id = ?1", nativeQuery = true)
    addressProjection findById2(Integer id);


}
