package com.venkat.coffeeshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.venkat.coffeeshop.model.Coffee;
import com.venkat.coffeeshop.model.PlaceCoffeeOrder;
import com.venkat.coffeeshop.model.TotalOrder;
import com.venkat.coffeeshop.service.ICoffeeOrderService;

@RestController
public class CoffeeController {
	
	@Autowired
	private ICoffeeOrderService iCoffeeOrderService;
	
	@GetMapping("/getAllCoffees")
	public ResponseEntity<List<Coffee>> getAllCoffee(){
		return new ResponseEntity<>(iCoffeeOrderService.getCoffees(), HttpStatus.OK);
	}
	
	@PutMapping("/updateCoffee")
	public ResponseEntity<String> updateCoffee(@RequestBody Coffee coffee){
		return new ResponseEntity<>(iCoffeeOrderService.updateCoffee(coffee), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteCoffee")
	public ResponseEntity<String> deleteCoffee(@RequestBody Coffee coffee){
		return new ResponseEntity<>(iCoffeeOrderService.deleteCoffee(coffee), HttpStatus.CREATED);
	}
	
	@PostMapping("/placeOrder")
	public ResponseEntity<String> orderCoffee(@RequestBody PlaceCoffeeOrder placeOder){
		return new ResponseEntity<>(iCoffeeOrderService.purchaseCoffee(placeOder), HttpStatus.OK);
	}
	
	@GetMapping("/getSummary")
	public ResponseEntity<List<TotalOrder>> getSummaryOfSales(){
		return new ResponseEntity<>(iCoffeeOrderService.getPurchaseDetails(), HttpStatus.OK);
	}

}
