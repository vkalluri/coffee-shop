package com.venkat.coffeeshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.venkat.coffeeshop.model.CoffeeOrder;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Integer> {

}
