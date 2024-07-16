package com.bookstore.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    
    @GetMapping("login-redirect")
    public ModelAndView loginRedirect(Model model) {
    	return new ModelAndView("redirect:/");
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
    	cart = orderService.save(cart);
    	user.setCart(cart);
    	orderService.save(cart);
    	userService.save(user);
    	return "cart.buy";
    }
    
    @GetMapping("/orders")
    public String orders(Model model) {
    	return "orders";
    }
}