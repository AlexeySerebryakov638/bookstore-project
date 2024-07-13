package com.bookstore.security;

import java.util.HashSet;
import java.util.Set;

import com.bookstore.Order;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
	
	@Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Nonnull
    private String username;
	@Nonnull
    private String password;
	@Nonnull
    private String role;
	@Nonnull
    private boolean enabled;
	
	@OneToOne
	Order cart;
	@OneToMany
	Set<Order> orders = new HashSet<>();
}
