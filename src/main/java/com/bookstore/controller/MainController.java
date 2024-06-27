package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.bookstore.Book;
import com.bookstore.providers.BookProvider;


@Controller
public class MainController {
	@Autowired
	BookProvider bookProvider;

    @PostMapping("/result/") 
    public String result(@ModelAttribute Book book) {
    	bookProvider.addBook(book);
    	return "result";
    }

    @GetMapping("/save/")
    public String save(Model model) {
    	return "booklist";
    }
    
    @GetMapping("/list/")
    public String list(Model model) {
    	return "booklist";
    }
    
    @GetMapping("/add/")
    public String add(Model model) {
    	model.addAttribute("book", new Book());
    	return "addform";
    }
    
}