package com.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.bookstore.controller.EmployeeController;
import com.bookstore.providers.BookRepository;

@WebMvcTest({EmployeeController.class, BookRepository.class})
public class EmployeeControllerTests {
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	BookRepository bookRepository;
	
	@Test
	void testPostNewBook() throws Exception {
		Book book = new Book(1, "Book 1", "Author", "Language", 1998, "Genre", "Description", "100100100100", 100, -1, true, "", 1000.0f, 100);
		
		mvc.perform(MockMvcRequestBuilders.post("/employee/save-book", book))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
