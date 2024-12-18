package com.github.sanchezih.booking.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.sanchezih.booking.dto.OrderDTO;
import com.github.sanchezih.booking.entity.Order;
import com.github.sanchezih.booking.repository.OrderRepository;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {
	@Autowired
	private OrderRepository orderRepository;

	@PostMapping("/order")
	public String saveOrder(@RequestBody OrderDTO orderDTO) {
		Order order = new Order();
		order.setOrderNo(UUID.randomUUID().toString());
		order.setOrderItems(orderDTO.getOrderItems());
		orderRepository.save(order);
		return "Order Saved";
	}

}