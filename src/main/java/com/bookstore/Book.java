package com.bookstore;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class Book implements Serializable {
	
	private static final long serialVersionUID = 201857762603516167L;

	@Getter @Setter
	private Integer id;
	@Getter @Setter
	private String name;
	@Getter @Setter
	private String author;
	@Getter @Setter
	private String language;
	@Getter @Setter
	private Integer year;
	@Getter @Setter
	private String genre;
	@Getter @Setter
	private String description;
	@Getter @Setter
	private String isbn;
	@Getter @Setter
	private Integer pages;
	@Getter @Setter
	private Integer rate;
	@Getter @Setter
	private Boolean new_book;
	@Getter @Setter
	private String image;
	@Getter @Setter
	private Float cost;
	@Getter @Setter
	private Integer amount;
	
	public Book(int id) {
		this.id = id;
	}
}
