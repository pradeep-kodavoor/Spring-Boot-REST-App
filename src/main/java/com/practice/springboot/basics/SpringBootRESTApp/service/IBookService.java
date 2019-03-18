package com.practice.springboot.basics.SpringBootRESTApp.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.practice.springboot.basics.SpringBootRESTApp.domain.Book;
import com.practice.springboot.basics.SpringBootRESTApp.domain.Book.ServiceValidation;

@Service
public interface IBookService {

	public Book getBookById(Long id);

	public List<Book> getListOfBooks();

	public List<Book> saveBooks(List<Book> list);

	@Validated(ServiceValidation.class)
	public Book addBook(@Valid Book book);

}
