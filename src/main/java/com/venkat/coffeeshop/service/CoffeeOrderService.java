package com.venkat.coffeeshop.service;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venkat.coffeeshop.dao.CoffeeOrderRepository;
import com.venkat.coffeeshop.dao.CoffeeRepository;
import com.venkat.coffeeshop.dao.DerivedQueriesDAO;
import com.venkat.coffeeshop.exception.CoffeeshopException;
import com.venkat.coffeeshop.model.CoffeWithOrder;
import com.venkat.coffeeshop.model.Coffee;
import com.venkat.coffeeshop.model.CoffeeOrder;
import com.venkat.coffeeshop.model.PlaceCoffeeOrder;
import com.venkat.coffeeshop.model.TotalOrder;
import com.venkat.coffeeshop.utils.CoffeeshopUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CoffeeOrderService implements ICoffeeOrderService{
	
	@Autowired
	private CoffeeRepository coffeeRepository;
	
	@Autowired
	private CoffeeOrderRepository coffeeOrderRepository;
	
	@Autowired
	private CoffeeshopUtils coffeeshopUtils;
	
	@Autowired
	private DerivedQueriesDAO derivedQueriesDAO;

	@Override
	public List<Coffee> getCoffees() {
		log.info("Calling the repository");
		return coffeeRepository.findAll();
	}

	@Override
	public String updateCoffee(Coffee coffee) {
		log.info("Coffe Object{}", coffee);
		coffeeRepository.save(coffee);
		return "Record update successfully";
	}

	@Override
	public List<TotalOrder> getPurchaseDetails() {
		List<TotalOrder> totalSales = new ArrayList<>();
		List<CoffeWithOrder> totalDetails = derivedQueriesDAO.getAllDetails();
		log.debug("Total Details {}", totalDetails);
		
		totalDetails.forEach(details ->{
			TotalOrder totalOrder = new TotalOrder();
			totalOrder.setCoffeName(details.getName());
			totalOrder.setTotalQuantity(details.getQuantity());
			totalOrder.setTotalPrice(details.getPrice() * details.getQuantity());
			totalSales.add(totalOrder);
		});
		
		return totalSales;
	}

	@Override
	public String purchaseCoffee(PlaceCoffeeOrder placeOrder) {
		Boolean isCardExpired = coffeeshopUtils.validateCreditCardExpiry(placeOrder.getExpiredMonthAndYear());
		if(isCardExpired) {
			throw new CoffeeshopException("Credit card expired", BAD_REQUEST);
		}
		Boolean isValidCard = coffeeshopUtils.validateCreditCardNumbeSize(placeOrder.getCcNumber());
		if(!isValidCard) {
			throw new CoffeeshopException("Credit card number is not valid", BAD_REQUEST);
		}
		placeOrder.getCoffeeList().forEach(coffee ->{
			CoffeeOrder order = new CoffeeOrder();
			order.setName(coffee.getCoffeeName());
			order.setQuantity(coffee.getQuantity());
			coffeeOrderRepository.save(order);
		});
		
		return "Order placed successfully";
		
	}

	@Override
	public String deleteCoffee(Coffee coffee) {
		coffeeRepository.delete(coffee);
		return "Record deleted successfully";
	}

}
