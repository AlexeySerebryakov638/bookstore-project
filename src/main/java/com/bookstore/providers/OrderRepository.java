package com.bookstore.providers;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.Order;

public interface OrderRepository extends CrudRepository<Order, Integer>{
	Iterable<Order> findByStatus(String status);
}
