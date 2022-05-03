package com.venkat.coffeeshop.service;

import java.util.List;

import com.venkat.coffeeshop.model.Coffee;
import com.venkat.coffeeshop.model.PlaceCoffeeOrder;
import com.venkat.coffeeshop.model.TotalOrder;

public interface ICoffeeOrderService {
	
	List<Coffee> getCoffees();
	String updateCoffee(Coffee coffee);
	String deleteCoffee(Coffee coffee);
	
	String purchaseCoffee(PlaceCoffeeOrder placeOrder);
	List<TotalOrder> getPurchaseDetails();

}
