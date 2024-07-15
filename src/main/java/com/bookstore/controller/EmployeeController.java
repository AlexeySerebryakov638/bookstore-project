package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookstore.Book;
import com.bookstore.providers.BookRepository;


@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	BookRepository bookRepository;
    
    @GetMapping("")
    public String root(Model model) {
    	return "employee.root";
    }
    
    @GetMapping("/add-book")
    public String addBook(Model model) {
    	model.addAttribute("book", new Book());
    	return "employee.add-book";
    }

    
    @GetMapping("/edit-book")
    public String editBook(Model model, @RequestParam Integer id) {
    	model.addAttribute("id", id);
    	return "employee.edit-book";
    }
    
    @PostMapping("/save-book")
    public String saveBook(@ModelAttribute Book book) {
    	bookRepository.save(book);
    	return "employee.save-book";
    }
}