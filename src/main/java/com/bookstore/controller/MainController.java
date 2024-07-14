package com.bookstore.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookstore.Book;
import com.bookstore.CartInsert;
import com.bookstore.Order;
import com.bookstore.providers.BookRepository;
import com.bookstore.providers.BookService;
import com.bookstore.providers.CartService;
import com.bookstore.providers.OrderService;
import com.bookstore.security.User;
import com.bookstore.security.UserService;


@Controller
public class MainController {
	@Autowired
	BookService bookService;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	OrderService orderService;
    
    @GetMapping("")
    public String root(Model model) {
    	return "root";
    }
    
    @GetMapping("/book")
    public String book(Model model, @RequestParam Integer id) {
    	Book book = bookService.findById(id).get();
    	model.addAttribute("book", book);
    	model.addAttribute("insert", new CartInsert());
    	model.addAttribute("curamount", cartService.getBookCount(book));
    	return "book";
    }
    
    @PostMapping("/buy")
    public String buy(@ModelAttribute CartInsert insert) {
    	cartService.addBook(insert);
    	return "buy";
    }
    
    @GetMapping("/cart")
    public String cart(Model model) {
    	return "cart";
    }
    
    @PostMapping("/cart/buy")
    public String cartBuy(Model model) {
    	User user = userService.getCurrentUser();
    	
    	Order cart = user.getCart();
    	cart.setStatus("in_progress");
    	orderService.save(cart);
    	user.getOrders().add(cart);
    	
    	cart = new Order(); // ...
    	user.setCart(cart);
    	cart = orderService.save(cart);
    	orderService.save(cart);
    	userService.save(user);
    	return "cart.buy";
    }
}