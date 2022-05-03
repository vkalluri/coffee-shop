package com.test.coffeeshop.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.venkat.coffeeshop.utils.CoffeeshopUtils;

@ExtendWith(SpringExtension.class)
public class CoffeeshopUtilsTest {

	@InjectMocks
	private CoffeeshopUtils coffeeshopUtils; 
	
	@Test
	public void testDateBefore() {
		Boolean value = coffeeshopUtils.validateCreditCardExpiry("03/22");
		assertTrue(value);
	}
	
	@Test
	public void testDateAfter() {
		Boolean value = coffeeshopUtils.validateCreditCardExpiry("10/25");
		assertFalse(value);
	}
	
	@Test
	public void testValidateCreditCardNumbeSizeForString() {
		Boolean value = coffeeshopUtils.validateCreditCardNumbeSize("This will return false");
		assertFalse(value);
	}
	
	@Test
	public void testValidateCreditCardNumbeSizeForNull() {
		Boolean value = coffeeshopUtils.validateCreditCardNumbeSize(null);
		assertFalse(value);
	}
	
	@Test
	public void testValidateCreditCardNumbeSizeFor16Number() {
		Boolean value = coffeeshopUtils.validateCreditCardNumbeSize("1234567890123456");
		assertTrue(value);
	}
	
	@Test
	public void testValidateCreditCardNumbeSizeForLessthan16Number() {
		Boolean value = coffeeshopUtils.validateCreditCardNumbeSize("12345678901256");
		assertFalse(value);
	}
	
	@Test
	public void testValidateCreditCardNumbeSizeForMorethan16Number() {
		Boolean value = coffeeshopUtils.validateCreditCardNumbeSize("12345678901234562213");
		assertFalse(value);
	}
	
}
