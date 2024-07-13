package com.bookstore.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookstore.CartInsert;
import com.bookstore.providers.BookRepository;
import com.bookstore.providers.CartProvider;
import com.bookstore.security.User;


@Controller
public class MainController {
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	CartProvider cartProvider;
    
    @GetMapping("")
    public String root(Model model) {
    	return "root";
    }
    
    @GetMapping("/book")
    public String book(Model model, @RequestParam Integer id) {
    	var book = bookRepository.findById(id).get();
    	model.addAttribute("book", book);
    	model.addAttribute("insert", new CartInsert());
    	model.addAttribute("curamount", cartProvider.getBookCount(book));
    	return "book";
    }
    
    
    @GetMapping("/cart")
    public String cart(Model model, @AuthenticationPrincipal UserDetails user) {
    	return "cart";
    }
    
    
    @PostMapping("/buy")
    public String buy(@ModelAttribute CartInsert insert) {
    	cartProvider.addBook(insert);
    	return "buy";
    }
}