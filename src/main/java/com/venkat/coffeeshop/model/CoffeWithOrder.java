package com.venkat.coffeeshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="coffee")
@SecondaryTable(name="coffee_order", pkJoinColumns=@PrimaryKeyJoinColumn(name="name"))
@Data
public class CoffeWithOrder {
	
	@Id
	@Column(name = "name")
	private String name;
	@Column(name = "price")
	private Double price;
	@Column(name="quantity", table="coffee_order")
	private Integer quantity;

}
