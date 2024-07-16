package com.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.test.context.support.WithMockUser;

import com.bookstore.providers.BookRepository;
import com.bookstore.providers.BookService;
import com.bookstore.providers.CartService;
import com.bookstore.providers.OrderService;
import com.bookstore.security.UserRepository;
import com.bookstore.security.UserService;
import com.bookstore.security.User;

@SpringBootTest
public class BookServiceTests {
	@Autowired
	BookService bookService;
	
	@Test
	void insertBooksTest() {
		Book book1 = new Book(1, "Book 1", "Author", "Language", 1998, "Genre", "Description", "100100100100", 100, -1, true, "", 1000.0f, 100);
		Book book2 = new Book(2, "Book 2");
		
		bookService.save(book1);
		bookService.save(book2);
		
		assertThat(bookService.findById(1).get().getName() == "Book 1");
		assertThat(bookService.findById(2).get().getName() == "Book 2");
		assertThat(bookService.findAll().spliterator().getExactSizeIfKnown() == 2);
	}
}
