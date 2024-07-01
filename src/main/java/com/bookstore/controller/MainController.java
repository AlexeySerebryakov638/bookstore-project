package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookstore.providers.BookProvider;
import com.bookstore.providers.CartProvider;


@Controller
public class MainController {
	@Autowired
	BookProvider bookProvider;

	@Autowired
	CartProvider cartProvider;
    
    @GetMapping("")
    public String list(Model model) {
    	return "booklist";
    }
    
    @GetMapping("/book")
    public String bookpage(Model model, @RequestParam Integer id) {
    	model.addAttribute("book", bookProvider.getBook(id));
    	model.addAttribute("amount", 1);
    	model.addAttribute("curamount", cartProvider.getBookCount(bookProvider.getBook(id)));
    	return "bookpage";
    }
    

    @GetMapping("/cart")
    public String cart(Model model) {
    	return "cartlist";
    }
    
    
    @PostMapping("/buy")
    public String buy(@RequestParam int id, @RequestParam int amount) {
    	cartProvider.addBook(bookProvider.getBook(id), amount);
    	return "buybook";
    }
}