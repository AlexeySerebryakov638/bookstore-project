package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookstore.security.User;
import com.bookstore.security.UserService;


@Controller
@RequestMapping("/user")
public class SecurityController {
	@Autowired
	UserService userService;
	
    @GetMapping("/register")
    public String book(Model model) {
    	model.addAttribute(new User());
    	return "register";
    }
    
    @PostMapping("/save-user")
    public String saveUser(@ModelAttribute User user) {
    	userService.save(user);
    	return "save-user";
    }
    
}