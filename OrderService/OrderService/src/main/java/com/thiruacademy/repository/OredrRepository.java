package com.thiruacademy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thiruacademy.entity.Order;

public interface OredrRepository extends JpaRepository<Order, Long>{

}
