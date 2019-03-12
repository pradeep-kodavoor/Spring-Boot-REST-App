package com.practice.springboot.basics.SpringBootRESTApp.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.practice.springboot.basics.SpringBootRESTApp.domain.Book;

@Repository
public class BookDao implements IBookDao {

	@Autowired
	EntityManager em;

	@Override
	public Book getBook(Long id) {
		return em.find(Book.class, id);
	}

	@Override
	public void saveBooks(List<Book> list) {
		list.stream().forEach(book -> em.persist(book));
		try {
			throw new SQLException();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Book> getBooksList() {
		return null;
	}

}
