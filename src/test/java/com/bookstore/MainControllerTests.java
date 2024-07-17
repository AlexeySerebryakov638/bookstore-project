package com.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.bookstore.controller.EmployeeController;
import com.bookstore.controller.MainController;
import com.bookstore.providers.BookRepository;
import com.bookstore.providers.BookService;
import com.bookstore.providers.CartService;
import com.bookstore.providers.OrderService;
import com.bookstore.security.SecurityConfig;
import com.bookstore.security.UserService;

@WebMvcTest({MainController.class})
@AutoConfigureMockMvc(addFilters = false)
public class MainControllerTests {
	@Autowired
	private MockMvc mvc;
	
	@MockBean(name="bookService")
	BookService bookService;
	@MockBean
	UserService userService;
	@MockBean(name="cartService")
	CartService cartService;
	@MockBean
	OrderService orderService;
	
	@Test
	@WithMockUser(authorities="employee")
	void testRoot() throws Exception {
		Mockito.when(bookService.findAll()).thenReturn(new ArrayList<Book>());
		mvc.perform(MockMvcRequestBuilders.get(""))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	@WithMockUser(authorities="employee")
	void testLoginToRootRedirect() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/login-redirect"))
			.andExpect(MockMvcResultMatchers.status().is3xxRedirection());
	}
	
	@Test
	@WithMockUser(authorities="employee")
	void testBook() throws Exception {
		Book book = new Book(1, "Book 1", "Author", "Language", 1998, "Genre", "Description", "100100100100", 100, -1, true, "", 1000.0f, 100);
		Mockito.when(bookService.findById(1)).thenReturn(Optional.of(book));
		mvc.perform(MockMvcRequestBuilders.get("/book?id=1"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	@WithMockUser(authorities="employee")
	void testBuy() throws Exception {		
		Book book = new Book(1, "Book 1", "Author", "Language", 1998, "Genre", "Description", "100100100100", 100, -1, true, "", 1000.0f, 100);
		Mockito.when(bookService.findById(1)).thenReturn(Optional.of(book));
		
		mvc.perform(MockMvcRequestBuilders.post("/buy", new CartInsert(1, 1)))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	@WithMockUser(authorities="employee")
	void testCart() throws Exception {
		Mockito.when(cartService.getBookList()).thenReturn(new HashSet<Record>());
		mvc.perform(MockMvcRequestBuilders.get("/cart"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
