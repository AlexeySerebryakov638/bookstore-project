package com.bookstore;

import lombok.Data;

@Data
public class CartInsert {
	private int id;
	private int amount;
	
	public CartInsert() {
	}
}
