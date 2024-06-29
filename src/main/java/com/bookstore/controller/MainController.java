package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bookstore.providers.BookProvider;


@Controller
public class MainController {
	@Autowired
	BookProvider bookProvider;
    
    @GetMapping("")
    public String list(Model model) {
    	return "booklist";
    }
}