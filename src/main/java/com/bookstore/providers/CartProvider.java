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

@Service
public class CartProvider {
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RecordRepository recordRepository;
	
	public CartProvider() {
	}
	
	public Set<Record> getBookList() {
		return getUserCartOrder().getRecords();
	}
	
	public Integer getBookCount(Book book) {
		Record record = recordRepository.findByOrderAndBook(getUserCartOrder(), book);
		if (record == null) return 0;
		return record.getAmount();
}
	
	public void addBook(CartInsert insert) {
		Book book = bookRepository.findById(insert.getId()).get();
		
		Record record = new Record();
		record.setBook(book);
		record.setAmount(insert.getAmount());
		recordRepository.save(record);
		
		User user = currentUser();
		user.getCart().getRecords().add(record);
		userRepository.save(user);
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
		User user = userRepository.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		return user;
	}
}
