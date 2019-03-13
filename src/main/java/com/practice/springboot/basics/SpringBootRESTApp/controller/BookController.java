package com.practice.springboot.basics.SpringBootRESTApp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practice.springboot.basics.SpringBootRESTApp.domain.Book;
import com.practice.springboot.basics.SpringBootRESTApp.service.IBookService;

@RestController
public class BookController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	IBookService bookService;

	@GetMapping("/books")
	public List<Book> getBooks() {
		return bookService.getListOfBooks();
	}

	@GetMapping("/book/{id}")
	public Book getBook(@PathVariable Long id) {
		return bookService.getBookById(id);
	}

	@PostMapping("/books")
	public ResponseEntity<Void> saveBooks(@RequestBody List<Book> list) {
		logger.info("Books Controller Invoked for saving books");
		if (list != null) {
			logger.info("Data Persisted in DB: " + bookService.saveBooks(list).toString());
			// throw new NullPointerException();
		}
		
		return ResponseEntity.accepted().build();
	}


}
