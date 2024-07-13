package com.bookstore.providers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bookstore.Book;
import com.bookstore.CartInsert;
import com.bookstore.Order;
import com.bookstore.Record;
import com.bookstore.security.User;
import com.bookstore.security.UserRepository;
import com.bookstore.security.UserService;

@Service
public class CartService {
	@Autowired
	BookService bookService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RecordService recordService;
	
	public CartService() {
	}
	
	public Set<Record> getBookList() {
		return getUserCartOrder().getRecords();
	}
	
	public Integer getBookCount(Book book) {
		Record record = recordService.findByOrderAndBook(getUserCartOrder(), book);
		if (record == null) return 0;
		return record.getAmount();
}
	
	public void addBook(CartInsert insert) {
		Book book = insert.getBook();
		int amount = insert.getAmount();
		
		Record record = new Record();
		record.setBook(book);
		record.setAmount(amount);
		recordService.save(record);
		
		User user = currentUser();
		user.getCart().getRecords().add(record);
		userService.save(user);
	}
	
	public float getTotalCost() {
		float ans = 0;
		for (Record r : getUserCartOrder().getRecords()) {	
			var book = r.getBook();
			ans += book.getCost() * r.getAmount();
		}
		return ans;
	}
	
	private Order getUserCartOrder() {
		User user = currentUser();
		return user.getCart();
	}
	
	private User currentUser() {
		User user = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		return user;
	}
}
