package com.venkat.coffeeshop.model;

import java.util.List;

import lombok.Data;

@Data
public class PlaceCoffeeOrder {
	private List<CoffeeDetails> coffeeList;
	private String ccNumber;
	private String expiredMonthAndYear;

}
