package com.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.Test;
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
import com.bookstore.providers.BookRepository;
import com.bookstore.providers.BookService;
import com.bookstore.security.SecurityConfig;
import com.bookstore.security.UserService;

@WebMvcTest({EmployeeController.class})
@AutoConfigureMockMvc(addFilters = false)
public class EmployeeControllerTests {
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	BookService bookService;
	
	@Test
	@WithMockUser(authorities="employee")
	void testPostNewBook() throws Exception {
		Book book = new Book(1, "Book 1", "Author", "Language", 1998, "Genre", "Description", "100100100100", 100, -1, true, "", 1000.0f, 100);
		mvc.perform(MockMvcRequestBuilders.post("/employee/save-book", book))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	@WithMockUser(authorities="employee")
	void testGetMenu() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/employee"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	

	@Test
	@WithMockUser(authorities="employee")
	void testGetAddBook() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/employee/add-book"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	@WithMockUser(authorities="employee")
	void testGetEditBook() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/employee/edit-book"))
			.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
}
