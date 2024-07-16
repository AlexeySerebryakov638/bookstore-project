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
		return orderRepository.save(order);
	}
	
	public Order submit(Order order) throws Exception {
		for (Record record : order.getRecords()) {
			if (record.getAmount() > record.getBook().getAmount()) {
				throw new Exception("Недостаточно книг в наличии!");
			}
		}

		for (Record record : order.getRecords()) {
			record.getBook().setAmount(record.getBook().getAmount() - record.getAmount());
		}

		return orderRepository.save(order);
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
