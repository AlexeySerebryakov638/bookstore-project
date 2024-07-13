package com.bookstore;

import java.util.HashSet;
import java.util.Set;

import com.bookstore.security.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	String status = "cart";
	
	@ManyToOne
	private User user;
	
	@OneToMany
	Set<Record> records = new HashSet<>();
	
}