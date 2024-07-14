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
		System.out.println(book);
		System.out.println(record);
		if (record == null) return 0;
		return record.getAmount();
}
	
	public void addBook(CartInsert insert) {
		Book book = bookService.findById(insert.getId()).get();
		int amount = insert.getAmount();
		User user = userService.getCurrentUser();
		
		Record record = new Record();
		record.setBook(book);
		record.setAmount(amount);
		
		Order cart = user.getCart();
		cart.getRecords().add(record);
		
		record = recordService.save(record);
		orderService.save(cart);
	}
	
	public float getTotalCost() {
		float ans = 0;
		for (Record r : userService.getCurrentUser().getCart().getRecords()) {	
			var book = r.getBook();
			ans += book.getCost() * r.getAmount();
		}
		return ans;
	}
}
