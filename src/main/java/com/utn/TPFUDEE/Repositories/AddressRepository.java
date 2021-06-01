package com.utn.TPFUDEE.Repositories;

import com.utn.TPFUDEE.Models.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    Page<Address> findAll(Pageable pageable);
}
