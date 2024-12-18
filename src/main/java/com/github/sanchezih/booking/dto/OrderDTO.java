package com.github.sanchezih.booking.dto;

import java.util.List;

import com.github.sanchezih.booking.entity.OrderItem;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {
	private List<OrderItem> orderItems;
}