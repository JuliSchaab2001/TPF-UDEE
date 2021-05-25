package com.utn.TPFUDEE.Repositories;

import com.utn.TPFUDEE.Models.Address;
import com.utn.TPFUDEE.Projections.addressProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    Page<Address> findAll(Pageable pageable);

    addressProjection findByStreet(String street);

    addressProjection findByStreet2(String street);
}
