package com.github.sanchezih.booking.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "pvs-stock-microservice")
public interface StockClient {
	
	@RequestMapping("/api/v1/stock/{code}")
	boolean stockAvailable(@PathVariable String code);
}