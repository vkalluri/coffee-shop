package com.venkat.coffeeshop.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class CoffeeshopUtils {
	private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
	public boolean validateCreditCardExpiry(String passedDate) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy");
			simpleDateFormat.setLenient(false);
			Date expiry = simpleDateFormat.parse(passedDate);
			return expiry.before(new Date());
		} catch (Exception ex) {

		}
		return false;
	}

	public boolean validateCreditCardNumbeSize(String ccNumber) {
		
		if (ccNumber == null) {
			return false;
		}else if(!pattern.matcher(ccNumber).matches()) {
			return false;
		}  else if (ccNumber.length() != 16) {
			return false;
		} else {
			return true;
		}
	}

}
