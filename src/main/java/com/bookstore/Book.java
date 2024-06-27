package com.bookstore;

import java.io.Serializable;

public class Book implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 201857762603516167L;

	static int new_id = 0;
	
	private String name;
	public String getName() { return name; }
	public void setName(String name) { this.name = name; };
	
	private String id;
	public String getId() { return id; }
	
	public Book() {
		this.id = "" + new_id++;
	}
}
