package com.practice.springboot.basics.SpringBootRESTApp.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.practice.springboot.basics.SpringBootRESTApp.domain.Book;
import com.practice.springboot.basics.SpringBootRESTApp.service.IBookService;

@RestController
@Validated
public class BookController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	IBookService bookService;

	@GetMapping("/books")
	public ResponseEntity<List<Book>> getBooks() {
		logger.info("Books Controller Invoked for getting list of books");

		List<Book> list = bookService.getListOfBooks();
		if (list != null && list.size() != 0) {
			return new ResponseEntity<>(list, HttpStatus.OK);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/book/{id}")
	public ResponseEntity<Book> getBook(@PathVariable Long id) {
		logger.info("Books Controller Invoked for getting a book for id: " + id);
		Book book = bookService.getBookById(id);

		if (book != null) {
			return new ResponseEntity<>(book, HttpStatus.OK);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping("/books")
	public ResponseEntity<Void> saveBooks(@Valid @RequestBody List<Book> list) {
		logger.info("Books Controller Invoked for saving books");
		if (list != null) {
			logger.info("Data Persisted in DB: " + bookService.saveBooks(list).toString());
		}
		return ResponseEntity.accepted().build();
	}
	
	@PostMapping("/book")
	public ResponseEntity<Void> addBook(@RequestBody Book book) {

		logger.info("Books Controller Invoked for adding new book");
		Book createdBook = bookService.addBook(book);

		if (createdBook == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdBook.getId())
				.toUri();

		return ResponseEntity.created(uri).build();

	}

}
