package com.bookstore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookstore.Order;
import com.bookstore.providers.OrderService;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	OrderService orderService;
	
	public User save(User user) {
    	Order order = new Order();
    	order = orderService.save(order);
    	user.setCart(order);
    	
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
    	user = userRepository.save(user);
    	return user;
	}
	
	public User getUserByUsername(String name) {
		return userRepository.getUserByUsername(name);
	}
	
	public User getCurrentUser() {
		return getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
	}
}
