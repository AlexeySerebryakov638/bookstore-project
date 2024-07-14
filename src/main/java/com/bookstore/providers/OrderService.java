package com.bookstore.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.Order;
import com.bookstore.Record;

@Service
public class OrderService {
	@Autowired
	OrderRepository orderRepository;
	
	public Order save(Order order) {
		order = orderRepository.save(order);
		return order;
	}
	
	public float getTotalCost(Order order) {
		float ans = 0;
		for (Record r : order.getRecords()) {	
			var book = r.getBook();
			ans += book.getCost() * r.getAmount();
		}
		return ans;
	}
}
