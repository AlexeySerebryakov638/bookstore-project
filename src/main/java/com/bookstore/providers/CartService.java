package com.bookstore.providers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.Book;
import com.bookstore.CartInsert;
import com.bookstore.Order;
import com.bookstore.Record;
import com.bookstore.security.User;
import com.bookstore.security.UserService;

@Service
public class CartService {
	@Autowired
	BookService bookService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RecordService recordService;
	
	@Autowired
	OrderService orderService;
	
	public CartService() {
	}
	
	public Set<Record> getBookList() {
		return userService.getCurrentUser().getCart().getRecords();
	}
	
	public Integer getBookCount(Book book) {
		Record record = recordService.findByOrderAndBook(userService.getCurrentUser().getCart(), book);
		if (record == null) return 0;
		return record.getAmount();
}
	
	public void addBook(CartInsert insert) {
		Book book = bookService.findById(insert.getId()).get();
		int amount = insert.getAmount();
		
		Order cart = userService.getCurrentUser().getCart();
		Record record = recordService.findByOrderAndBook(cart, book);
		if (record == null) {
			record = new Record();
			record.setBook(book);
			record.setAmount(amount);
			record.setOrder(cart);
			record = recordService.save(record);
			cart.getRecords().add(record);
			cart = orderService.save(cart);
		} else {
			record.setAmount(record.getAmount() + amount);
			record = recordService.save(record);
		}
		
	}
	
	public float getTotalCost() {
		return orderService.getTotalCost(userService.getCurrentUser().getCart());
	}
}
