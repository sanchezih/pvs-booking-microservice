package com.github.sanchezih.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.sanchezih.booking.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}