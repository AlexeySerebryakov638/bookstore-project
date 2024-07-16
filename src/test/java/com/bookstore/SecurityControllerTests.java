package com.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.bookstore.controller.SecurityController;
import com.bookstore.security.User;
import com.bookstore.security.UserService;

@WebMvcTest({SecurityController.class})
@AutoConfigureMockMvc(addFilters = false)
public class SecurityControllerTests {
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	UserService userService;

	@Test
	@WithMockUser(authorities="employee")
	void testGetMenu() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/user/register"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	@WithMockUser(authorities="employee")
	void testPostNewBook() throws Exception {
		User user = new User();
		user.setUsername("test");
		user.setPassword("test");
		user.setRole("employee");
		mvc.perform(MockMvcRequestBuilders.post("/user/save-user", user))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
