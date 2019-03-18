package com.practice.springboot.basics.SpringBootRESTApp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Book {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull(message = "Name cannot be null for a book")
	@Size(min = 2, message = "Book name should have atleast 2 characters")
	private String name;
	@NotNull(message = "Book cannot be added without specifying the name of the author")
	private String author;

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(Long id, String name, String author) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
	}

	public Book(String name, String author) {
		super();
		this.name = name;
		this.author = author;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author + "]";
	}

}
