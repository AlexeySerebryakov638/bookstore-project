package com.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.providers.BookProvider;
import com.bookstore.providers.CartProvider;

@RestController
@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean(name="bookProvider")
	public BookProvider getBookProvider() {
		return new BookProvider();
	}

	@Bean(name="cartProvider")
	public CartProvider getCartProvider() {
		return new CartProvider();
	}
}
