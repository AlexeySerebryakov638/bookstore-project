package com.bookstore.providers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bookstore.Book;
import com.bookstore.CartInsert;

public class CartProvider {
	@Autowired
	BookRepository bookRepository;
	
	Map<Integer, Integer> bookList;
	
	public CartProvider() {
		bookList = new HashMap<>();
	}
	
	public Map<Integer, Integer> getBookList() {
		return bookList;
	}
	
	public Integer getBookCount(int id) {
		return bookList.getOrDefault(id, 0);
	}
	
	public void addBook(CartInsert insert) {
		bookList.put(insert.getId(), bookList.getOrDefault(insert.getId(), 0) + insert.getAmount());
		if (bookList.get(insert.getId()) <= 0) {
			bookList.remove(insert.getId());
			}
	}
	
	public int getId() {
		return bookList.size();
	}
	
	public float getTotalCost() {
		float ans = 0;
		for (int id : bookList.keySet()) {	
			var book = bookRepository.findById(id);
			if (book.isPresent()) ans += book.get().getCost() * bookList.get(id);
		}
		return ans;
	}
}
