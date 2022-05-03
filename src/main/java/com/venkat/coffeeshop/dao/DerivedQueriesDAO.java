package com.venkat.coffeeshop.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venkat.coffeeshop.model.CoffeWithOrder;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DerivedQueriesDAO {

	@Autowired
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<CoffeWithOrder> getAllDetails() {
		Query query = entityManager.createNativeQuery(
				"Select c.name, c.price, o.quantity from coffee c, coffee_order o where c.name = o.name",
				CoffeWithOrder.class);
		log.info("Calling DB");
		List<CoffeWithOrder> totalDetails = (List<CoffeWithOrder>) query.getResultList();
		log.info("Returned data {}", totalDetails);
		return totalDetails;
	}

}
