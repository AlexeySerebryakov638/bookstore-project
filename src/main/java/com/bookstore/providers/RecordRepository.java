package com.bookstore.providers;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.Book;
import com.bookstore.Order;
import com.bookstore.Record;

public interface RecordRepository extends CrudRepository<Record, Integer>{
	Record findByOrderAndBook(Order order, Book book);
}
