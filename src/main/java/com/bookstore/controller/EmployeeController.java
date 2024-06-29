package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookstore.Book;
import com.bookstore.providers.BookProvider;


@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	BookProvider bookProvider;
    
    @GetMapping("")
    public String employee(Model model) {
    	return "employeemenu";
    }
    
    @GetMapping("/add-book")
    public String add(Model model) {
    	model.addAttribute("book", new Book());
    	return "addbook";
    }
    
    @PostMapping("/save-book")
    public String save(@ModelAttribute Book book) {
    	bookProvider.addBook(book);
    	return "savebook";
    }
}