package com.practice.springboot.basics.SpringBootRESTApp.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.practice.springboot.basics.SpringBootRESTApp.dao.BookRespository;
import com.practice.springboot.basics.SpringBootRESTApp.domain.Book;
import com.practice.springboot.basics.SpringBootRESTApp.domain.Book.ServiceValidation;

@Service
@Validated
public class BookService implements IBookService {

	Logger logger = LoggerFactory.getLogger(BookService.class);

	@Autowired
	BookRespository bookRepository;

	@Override
	public Book getBookById(Long id) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		return optionalBook.isPresent() ? optionalBook.get() : null;
	}

	@Override
	public List<Book> getListOfBooks() {
		return bookRepository.findAll();
	}

	@Override
	public List<Book> saveBooks(List<Book> list) {
		logger.info("Books Service Invoked for saving books");
		return bookRepository.saveAll(list);
	}

	@Override
	@Validated(ServiceValidation.class)
	public Book addBook(@Valid Book book) {
		logger.info("Books Service Invoked for adding new book");
		return bookRepository.save(book);
	}
}
