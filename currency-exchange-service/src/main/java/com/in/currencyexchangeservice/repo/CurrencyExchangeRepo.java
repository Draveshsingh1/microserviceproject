package com.in.currencyexchangeservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in.currencyexchangeservice.bean.CurrencyExchange;


public interface CurrencyExchangeRepo extends JpaRepository<CurrencyExchange, Long>{
	
	CurrencyExchange findByFromAndTo(String from, String to);

}
