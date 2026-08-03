package com.vti.repository;

import com.vti.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IDishRepository extends JpaRepository<Dish, Integer>, JpaSpecificationExecutor<Dish> {
    boolean existsByName(String name);
}
