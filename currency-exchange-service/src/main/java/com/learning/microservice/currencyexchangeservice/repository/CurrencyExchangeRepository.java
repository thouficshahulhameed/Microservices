package com.learning.microservice.currencyexchangeservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.microservice.currencyexchangeservice.model.CurrencyExchange;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {

	CurrencyExchange findByFromAndTo(String from, String to);
	
	
	
	

}
