package com.bookstore.providers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bookstore.Book;
import com.bookstore.CartInsert;

public class CartProvider {
	@Autowired
	BookProvider bookProvider;
	
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
		System.out.println(insert.getId() + " " + insert.getAmount());
	}
	
	public int getId() {
		return bookList.size();
	}
}
