package com.bookstore.providers;

import java.util.HashMap;
import java.util.Map;

import com.bookstore.Book;

public class CartProvider {
	Map<Book, Integer> bookList;
	
	public CartProvider() {
		bookList = new HashMap<>();
	}
	
	public Map<Book, Integer> getBookList() {
		return bookList;
	}
	
	public Integer getBookCount(Book book) {
		return bookList.getOrDefault(book, 0);
	}
	
	public void addBook(Book book, int amount) {
		bookList.put(book, bookList.getOrDefault(book, 0) + amount);
	}
	
	public int getId() {
		return bookList.size();
	}
}
