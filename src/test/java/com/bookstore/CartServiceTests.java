package com.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.test.context.support.WithMockUser;

import com.bookstore.providers.BookRepository;
import com.bookstore.providers.CartService;
import com.bookstore.providers.OrderService;
import com.bookstore.security.UserRepository;
import com.bookstore.security.UserService;
import com.bookstore.security.User;

@SpringBootTest
public class CartServiceTests {
	
	@Autowired
	BookRepository bookRepository;
	@Autowired
	CartService cartService;
	@Autowired
	OrderService orderService;
	@MockBean
	UserService userService;

	@Test
	void insertBooksIntoCartTest() {	
		User user = new User();
		user.setCart(new Order());
		orderService.save(user.getCart());
		Mockito.when(userService.getCurrentUser()).thenReturn(user);
		
		Book book = new Book();
		book.setId(1);
		cartService.addBook(new CartInsert(book.getId(), 10));
		cartService.addBook(new CartInsert(book.getId(), 20));
		
		Book book2 = new Book();
		book2.setId(2);
		
		assertThat(cartService.getBookCount(book) == 30);
		assertThat(cartService.getBookCount(book2) == 0);
		assertThat(cartService.getBookList().size() == 1);
	}
	
	@Test
	void getTotalCostInCartTest() {
		User user = new User();
		user.setCart(new Order());
		orderService.save(user.getCart());
		Mockito.when(userService.getCurrentUser()).thenReturn(user);
		
		Book book1 = new Book(1);
		
		book1.setCost(20.0f);
		
		bookRepository.save(book1);
		
		cartService.addBook(new CartInsert(book1.getId(), 10));
		
		assertThat(cartService.getTotalCost() == 200.0f);
	}
}
