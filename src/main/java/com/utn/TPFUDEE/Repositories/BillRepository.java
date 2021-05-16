package com.utn.TPFUDEE.Repositories;

import com.utn.TPFUDEE.Models.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<Bill,Integer> {
    Page<Bill> findAll(Pageable pageable);
}
