package com.bookstore;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class Book implements Serializable {
	
	private static final long serialVersionUID = 201857762603516167L;

	static int new_id = 0;
	
	@Getter @Setter
	private String name;
	
	@Getter @Setter
	private String id;
	
	@Getter @Setter
	private String author;
	
	public Book() {
		this.id = "" + new_id++;
	}
}
