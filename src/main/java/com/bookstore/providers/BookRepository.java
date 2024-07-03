package com.bookstore.providers;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.Book;

@Repository 
public interface BookRepository extends CrudRepository<Book, Integer> {
}