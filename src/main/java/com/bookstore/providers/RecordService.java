package com.bookstore.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.Book;
import com.bookstore.Order;
import com.bookstore.Record;

@Service
public class RecordService {
	@Autowired
	RecordRepository recordRepository;
	
	public Record save(Record record) {
		record = recordRepository.save(record);
		return record;
	}
	public Record findByOrderAndBook(Order order, Book book) {
		return recordRepository.findByOrderAndBook(order, book);
	}
}
