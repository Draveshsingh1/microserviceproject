package com.in.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.in.currencyconversionservice.bean.CurrncyConversion;
import com.in.currencyconversionservice.proxy.CurrencyExchangeProxy;

@RestController
public class CurrncyConversionController {
	@Autowired
	private Environment environment;
	@Autowired
	private CurrencyExchangeProxy currencyExchangeProxy;

	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrncyConversion calculateCurrncyConversion(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		// return new CurrncyConversion(10001L, from, to, BigDecimal.ONE, quantity,
		// BigDecimal.ONE, "");

		HashMap<String, String> uriVarriable = new HashMap<>();
		uriVarriable.put("from", from);
		uriVarriable.put("to", to);

		ResponseEntity<CurrncyConversion> currencyExchange = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrncyConversion.class, uriVarriable);
		CurrncyConversion data = currencyExchange.getBody();

		return new CurrncyConversion(data.getId(), from, to, data.getConversionMultiple(), quantity,
				quantity.multiply(data.getConversionMultiple()), environment.getProperty("server.port"));

	}

	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrncyConversion calculateCurrncyConversionUsingFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		CurrncyConversion currencyExchange = currencyExchangeProxy.retriveExchangeValue(from, to);

		return new CurrncyConversion(currencyExchange.getId(), from, to, currencyExchange.getConversionMultiple(),
				quantity, quantity.multiply(currencyExchange.getConversionMultiple()),
				environment.getProperty("server.port"));

	}

}
