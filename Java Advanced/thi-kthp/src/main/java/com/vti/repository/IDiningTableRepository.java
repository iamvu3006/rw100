package com.vti.repository;

import com.vti.entity.DiningTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDiningTableRepository extends JpaRepository<DiningTable, Integer> {
    boolean existsByTableNumber(String tableNumber);
}
