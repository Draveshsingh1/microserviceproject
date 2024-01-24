package com.in.currencyexchangeservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.in.currencyexchangeservice.bean.CurrencyExchange;
import com.in.currencyexchangeservice.repo.CurrencyExchangeRepo;

@RestController
public class CurrencyExchangeController {
	@Autowired
	private CurrencyExchangeRepo currencyExchangeRepo;
	@Autowired
	private Environment environment;
	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retriveExchangeValue(@PathVariable String from, @PathVariable String to) {
		
		String port = environment.getProperty("local.server.port");
		
		//CurrencyExchange currencyExchange= new CurrencyExchange(100L, from, to,BigDecimal.valueOf(50));
		CurrencyExchange currencyExchange=currencyExchangeRepo.findByFromAndTo(from, to);
		currencyExchange.setEnvironment(port);
		return currencyExchange;
		
	}

}