package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.Book;
import com.bookstore.providers.BookService;

@RestController
@RequestMapping("/api")
public class ApiController {
	@Autowired
	BookService bookService;
	
	@GetMapping("/book/{id}")
	public Book book(@PathVariable Integer id) {
		Book book = bookService.findById(id).get();
		return book;
	}
	
	@GetMapping("/books")
	public Iterable<Book> allBooks() {
		return bookService.findAll();
	}
}
