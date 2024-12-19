package com.github.sanchezih.booking.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.sanchezih.booking.client.StockClient;
import com.github.sanchezih.booking.dto.OrderDTO;
import com.github.sanchezih.booking.entity.Order;
import com.github.sanchezih.booking.repository.OrderRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private StockClient stockClient;

	@PostMapping("/order")
	@HystrixCommand(fallbackMethod = "fallbackToStockService")
	public String saveOrder(@RequestBody OrderDTO orderDTO) {

		boolean inStock = orderDTO.getOrderItems().stream()
				.allMatch(orderItem -> stockClient.stockAvailable(orderItem.getCode()));

		if (inStock) {
			Order order = new Order();

			order.setOrderNo(UUID.randomUUID().toString());
			order.setOrderItems(orderDTO.getOrderItems());

			orderRepository.save(order);

			return "Order Saved";
		}

		return "Order Cannot be Saved";
	}

	private String fallbackToStockService() {
		return "Something went wrong, please try after some time";
	}

}