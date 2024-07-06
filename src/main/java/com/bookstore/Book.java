package com.bookstore;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="BOOKS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {
	
	private static final long serialVersionUID = 201857762603516167L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String name;
	private String author;
	private String language;
	private Integer publicationYear;
	private String genre;
	private String description;
	private String isbn;
	private Integer pages;
	private Integer rate;
	private Boolean newBook;
	private String image;
	private Float cost;
	private Integer amount;

	public Book(int id) {
		this.id = id;
	}

	public Book(int id, String name) {
		this.id = id;
		this.name = name;
	}
}
