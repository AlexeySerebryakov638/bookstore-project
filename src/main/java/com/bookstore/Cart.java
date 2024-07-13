package com.bookstore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.bookstore.security.User;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
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
@Table(name="carts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne
	User user;
	
	@OneToMany
	Set<Record> records = new HashSet<>();
}
