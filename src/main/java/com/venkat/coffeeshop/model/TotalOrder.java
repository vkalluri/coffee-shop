package com.venkat.coffeeshop.model;

import lombok.Data;

@Data
public class TotalOrder {
	private String coffeName;
	private Integer totalQuantity;
	private Double totalPrice;
}
