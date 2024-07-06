package com.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bookstore.providers.BookRepository;

@SpringBootTest
class BookRepositoryTests {
	
	@Autowired
	BookRepository bookRepository;

	@Test
	void getBookByIdTest() {
		Book book1 = new Book(1, "Book 1");
		Book book2 = new Book(2, "Book 2");
		Book book3 = new Book(3, "Book 3");
		
		bookRepository.save(book1);
		bookRepository.save(book2);
		bookRepository.save(book3);
		
		assertThat(bookRepository.findById(1).get().getName() == book1.getName());
		assertThat(bookRepository.findById(2).get().getName() == book2.getName());
		assertThat(bookRepository.findById(4).isEmpty());
	}

}
