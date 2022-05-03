package com.venkat.coffeeshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Entity
@Table("coffee")
public class Coffee {
	@Id
	@Column(name = "name")
	private String name;
	@Column(name = "price")
	private Double price;

}
