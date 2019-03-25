package com.practice.springboot.basics.SpringBootRESTApp.dao;

import java.util.List;

import com.practice.springboot.basics.SpringBootRESTApp.domain.Book;

public interface IBookDao {

	public Book getBook(Long id);

	public void saveBooks(List<Book> list);

}
