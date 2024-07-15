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
//		if (book.getAuthor() == null) book.setAuthor("");
//		if (book.getPublicationYear() == null) book.setPublicationYear(2000);
//		if (book.getDescription() == null) book.setDescription("");
//		if (book.getIsbn() == null) book.setIsbn("");
//		if (book.getPages() == null) book.setPages(0);
//		if (book.getRate() == null) book.setRate(-1);
//		if (book.getCost() == null) book.setCost(0.0f);
//		if (book.getAmount() == null) book.setAmount(0);
		book = bookRepository.save(book);
		return book;
	}
	
	public Optional<Book> findById(Integer id) {
		return bookRepository.findById(id);
	}
	
	public Iterable<Book> findAll() {
		return bookRepository.findAll();
	}
}
