package com.bookstore;

import lombok.Getter;
import lombok.Setter;

public class CartInsert {
	@Getter @Setter
	private int id;
	@Getter @Setter
	private int amount;
	
	public CartInsert() {
	}
}
