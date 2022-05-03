package com.venkat.coffeeshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Entity
@Table("coffee_order")
public class CoffeeOrder {
	@Id
	@Column(name = "name")
	private String name;
	@Column(name = "quantity")
	private Integer quantity; 

}
