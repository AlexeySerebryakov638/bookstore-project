package com.bookstore.providers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.Book;

@Service
public class BookService {
	@Autowired
	BookRepository bookRepository;
	
	public Book save(Book book) {
		book = bookRepository.save(book);
		return book;
	}
	
	public Optional<Book> findById(Integer id) {
		return bookRepository.findById(id);
	}
}
