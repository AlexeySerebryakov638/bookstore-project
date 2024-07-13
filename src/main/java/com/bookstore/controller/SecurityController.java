package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookstore.Order;
import com.bookstore.providers.OrderRepository;
import com.bookstore.security.User;
import com.bookstore.security.UserRepository;

import jakarta.persistence.Transient;


@Controller
@RequestMapping("/user")
public class SecurityController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	
    @GetMapping("/register")
    public String book(Model model) {
    	model.addAttribute(new User());
    	return "register";
    }
    
    @PostMapping("/save-user")
    public String saveUser(@ModelAttribute User user) {
    	// ужасно
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
    	Order order = new Order();
    	orderRepository.save(order);
    	user.setCart(order);
    	userRepository.save(user);
    	return "save-user";
    }
    
}