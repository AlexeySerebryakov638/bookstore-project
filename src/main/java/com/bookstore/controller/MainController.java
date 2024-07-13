package com.bookstore.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookstore.CartInsert;
import com.bookstore.providers.BookRepository;
import com.bookstore.providers.CartService;


@Controller
public class MainController {
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	CartService cartService;
    
    @GetMapping("")
    public String root(Model model) {
    	return "root";
    }
    
    @GetMapping("/book")
    public String book(Model model, @RequestParam Integer id) {
    	var book = bookRepository.findById(id).get();
    	model.addAttribute("book", book);
    	model.addAttribute("insert", new CartInsert());
    	model.addAttribute("curamount", cartService.getBookCount(book));
    	return "book";
    }
    
    
    @GetMapping("/cart")
    public String cart(Model model) {
    	return "cart";
    }
    
    
    @PostMapping("/buy")
    public String buy(@ModelAttribute CartInsert insert) {
    	cartService.addBook(insert);
    	return "buy";
    }
}