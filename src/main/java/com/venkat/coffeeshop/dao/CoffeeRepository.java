package com.venkat.coffeeshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.venkat.coffeeshop.model.Coffee;

public interface CoffeeRepository extends JpaRepository<Coffee, Integer> {
	
	Coffee findByName(String coffeeName);

}
