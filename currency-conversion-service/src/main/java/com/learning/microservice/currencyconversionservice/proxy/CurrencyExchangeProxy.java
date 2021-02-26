package com.learning.microservice.currencyconversionservice.proxy;



import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.learning.microservice.currencyconversionservice.model.CurrencyConversion;



@FeignClient(name="currency-exchange")
public interface CurrencyExchangeProxy {
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retriveExchangeValue(
			@PathVariable String from,
			@PathVariable String to);	
	
	@GetMapping("/currency-exchange/all")
	public List<CurrencyConversion> retriveAll();

}
