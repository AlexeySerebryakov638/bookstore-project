package com.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bookstore.providers.BookRepository;
import com.bookstore.providers.CartService;

@SpringBootTest
public class CartTests {
	
	@Autowired
	BookRepository bookRepository;
	@Autowired
	CartService cartProvider;

	@Test
	void insertBooksIntoCartTest() {		
		cartProvider.addBook(new CartInsert(1, 10));
		cartProvider.addBook(new CartInsert(2, 20));
		
		assertThat(cartProvider.getBookCount(1) == 10);
		assertThat(cartProvider.getBookCount(2) == 20);
		assertThat(cartProvider.getBookCount(3) == 0);
	}
	
	@Test
	void getTotalCostInCartTest() {
		Book book1 = new Book(1);
		Book book2 = new Book(2);
		
		book1.setCost(20.0f);
		book2.setCost(100.0f);
		
		bookRepository.save(book1);
		bookRepository.save(book2);
		
		cartProvider.addBook(new CartInsert(book1.getId(), 10));
		cartProvider.addBook(new CartInsert(book2.getId(), 20));
		
		assertThat(cartProvider.getTotalCost() == 20.0 * 10 + 100.0 * 20);
	}
}
