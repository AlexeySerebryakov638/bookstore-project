package com.bookstore.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.Order;

@Service
public class OrderService {
	@Autowired
	OrderRepository orderRepository;
	
	public Order save(Order order) {
		order = orderRepository.save(order);
		return order;
	}
}
