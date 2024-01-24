package com.in.currencyconversionservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.in.currencyconversionservice.bean.CurrncyConversion;

//@FeignClient(name = "currency-exchange-service",url="localhost:8000")
@FeignClient(name = "currency-exchange-service")
public interface CurrencyExchangeProxy {
	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public CurrncyConversion retriveExchangeValue(@PathVariable String from, @PathVariable String to);
	

}
