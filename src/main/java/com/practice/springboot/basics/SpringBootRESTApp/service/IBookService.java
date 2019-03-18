package com.practice.springboot.basics.SpringBootRESTApp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.practice.springboot.basics.SpringBootRESTApp.domain.Book;

@Service
public interface IBookService {

	public Book getBookById(Long id);

	public List<Book> getListOfBooks();

	public List<Book> saveBooks(List<Book> list);

	public Book addBook(Book book);

}
