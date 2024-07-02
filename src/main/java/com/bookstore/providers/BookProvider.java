package com.bookstore.providers;

import java.util.ArrayList;

import com.bookstore.Book;

public class BookProvider {
	private ArrayList<Book> bookList;
	public BookProvider() {
		bookList = new ArrayList<>();
	}
	
	public ArrayList<Book> getBookList() {
		return bookList;
	}
	
	public Book getBook(int id) {
		return bookList.get(id);
	}
	
	public void addBook(Book book) {
		bookList.add(book);
	}
	
	public int getId() {
		return bookList.size();
	}
}
