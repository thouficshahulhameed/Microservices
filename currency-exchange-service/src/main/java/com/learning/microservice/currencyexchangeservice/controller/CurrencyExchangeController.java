package com.learning.microservice.currencyexchangeservice.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.learning.microservice.currencyexchangeservice.model.CurrencyExchange;
import com.learning.microservice.currencyexchangeservice.repository.CurrencyExchangeRepository;


@RestController
public class CurrencyExchangeController {

	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepositroy;
	
	@Autowired
	private Environment environment;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retriveExchangeValue(
			@PathVariable String from,
			@PathVariable String to) {
		//Optional<String> st;
		CurrencyExchange currencyExchange=new CurrencyExchange();
		
		Optional<CurrencyExchange> currencyExchangeResult =Optional.ofNullable(currencyExchangeRepositroy.findByFromAndTo(from, to));
		if(currencyExchangeResult.isPresent())
		{
			currencyExchange=currencyExchangeResult.get();
		}else
		{
			throw new RuntimeException("Unable to find date "+ from + " and " +"to");
			
		}
		
		String port=environment.getProperty("local.server.port");
		
		currencyExchange.setEnvironment(port);
		return currencyExchange;
	}
	
	@GetMapping("/currency-exchange/all")
	public List<CurrencyExchange> retriveAll(){
		
		List<CurrencyExchange> currencyExchangeList=new ArrayList<CurrencyExchange>();
		
		currencyExchangeList=currencyExchangeRepositroy.findAll();
 		
		return currencyExchangeList;
	}
}
