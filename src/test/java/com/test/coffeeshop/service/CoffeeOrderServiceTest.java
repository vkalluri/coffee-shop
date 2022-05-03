package com.test.coffeeshop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.venkat.coffeeshop.dao.CoffeeOrderRepository;
import com.venkat.coffeeshop.dao.CoffeeRepository;
import com.venkat.coffeeshop.dao.DerivedQueriesDAO;
import com.venkat.coffeeshop.exception.CoffeeshopException;
import com.venkat.coffeeshop.model.CoffeWithOrder;
import com.venkat.coffeeshop.model.Coffee;
import com.venkat.coffeeshop.model.CoffeeDetails;
import com.venkat.coffeeshop.model.PlaceCoffeeOrder;
import com.venkat.coffeeshop.model.TotalOrder;
import com.venkat.coffeeshop.service.CoffeeOrderService;
import com.venkat.coffeeshop.utils.CoffeeshopUtils;

@ExtendWith(MockitoExtension.class)
public class CoffeeOrderServiceTest {
	@Mock
	private CoffeeRepository coffeeRepository;
	
	@Mock
	private CoffeeOrderRepository coffeeOrderRepository;
	
	@Mock
	private CoffeeshopUtils coffeeshopUtils;
	
	@Mock
	private DerivedQueriesDAO derivedQueriesDAO;
	
	@InjectMocks
	private CoffeeOrderService coffeeOrderService;
	
	@Test
	public void getCoffeesForAllCoffees() {
		when(coffeeRepository.findAll()).thenReturn(getCoffees());
		 List<Coffee> listOfCoffees = coffeeOrderService.getCoffees();
		 assertNotNull(listOfCoffees);
	}
	
	@Test
	public void getCoffeesForNull() {
		when(coffeeRepository.findAll()).thenReturn(null);
		 List<Coffee> listOfCoffees = coffeeOrderService.getCoffees();
		 assertNull(listOfCoffees);
	}
	
	@Test
	public void updateCoffeeTest() {
		Coffee coffee = new Coffee();
		coffee.setName("name");
		coffee.setPrice(1.0);
		String str = coffeeOrderService.updateCoffee(coffee);
		assertNotNull(str);
		assertEquals(str,  "Record update successfully");
	}
	
	@Test
	public void deleteCoffeeTest() {
		Coffee coffee = new Coffee();
		coffee.setName("name");
		coffee.setPrice(1.0);
		String str = coffeeOrderService.deleteCoffee(coffee);
		assertNotNull(str);
		assertEquals(str,  "Record deleted successfully");
	}
	
	@Test
	public void getPurchaseDetailsTest() {
		when(derivedQueriesDAO.getAllDetails()).thenReturn(getDetails());
		List<TotalOrder> totalOrder = coffeeOrderService.getPurchaseDetails();
		assertNotNull(totalOrder);
		assertEquals(totalOrder.get(0).getTotalPrice(), 4);
		assertEquals(totalOrder.get(0).getTotalQuantity(), 4);
		
	}
	
	@Test
	public void purchaseCoffeeTestShouldThrowCardExpiredExcepion() {
		PlaceCoffeeOrder placeOrder = new PlaceCoffeeOrder();
		placeOrder.setExpiredMonthAndYear("01/22");
		placeOrder.setCcNumber("1234567890123456");
		placeOrder.setCoffeeList(new ArrayList<CoffeeDetails>());
		
		CoffeeshopException exception = assertThrows(CoffeeshopException.class, () ->{
			coffeeOrderService.purchaseCoffee(placeOrder);
		});
		assertNotNull(exception);
		
	}
	
	
	@Test
	public void purchaseCoffeeTestShouldThrowCardNotValidExcepion() {
		PlaceCoffeeOrder placeOrder = new PlaceCoffeeOrder();
		placeOrder.setExpiredMonthAndYear("01/30");
		placeOrder.setCcNumber("1234567890456");
		placeOrder.setCoffeeList(new ArrayList<CoffeeDetails>());
		
		CoffeeshopException exception = assertThrows(CoffeeshopException.class, () ->{
			coffeeOrderService.purchaseCoffee(placeOrder);
		});
		assertNotNull(exception);
		
	}
	
	
	private List<Coffee> getCoffees(){
		List<Coffee> coffeeList = new ArrayList<>();
		Coffee coffee = new Coffee();
		coffee.setName("name");
		coffee.setPrice(1.0);
		coffeeList.add(coffee);
		coffee = new Coffee();
		coffee.setName("name1");
		coffee.setPrice(2.0);
		coffeeList.add(coffee);
		return coffeeList;
	}
	
	private List<CoffeWithOrder> getDetails() {
		List<CoffeWithOrder> totalDetails = new ArrayList<>();
		CoffeWithOrder order = new CoffeWithOrder();
		order.setName("name");
		order.setPrice(1.0);
		order.setQuantity(4);
		totalDetails.add(order);
		order = new CoffeWithOrder();
		order.setName("name1");
		order.setPrice(3.0);
		order.setQuantity(6);
		totalDetails.add(order);
		
		return totalDetails;
	}

}
