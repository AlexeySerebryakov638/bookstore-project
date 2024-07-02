package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookstore.CartInsert;
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
    	var book = bookProvider.getBook(id);
    	model.addAttribute("book", book);
    	model.addAttribute("insert", new CartInsert());
    	model.addAttribute("curamount", cartProvider.getBookCount(id));
    	return "bookpage";
    }
    

    @GetMapping("/cart")
    public String cart(Model model) {
    	return "cartlist";
    }
    
    
    @PostMapping("/buy")
    public String buy(@ModelAttribute CartInsert insert) {
    	cartProvider.addBook(insert);
    	return "buybook";
    }
}