package com.practice.springboot.basics.SpringBootRESTApp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.practice.springboot.basics.SpringBootRESTApp.util.AuthorName;

@Entity
public class Book {

	@Id
	@GeneratedValue
	@NotNull(groups = UpdateBookValidation.class, message = "ID cannot be null while updating a book")
	private Long id;

	@NotNull(groups = ControllerValidation.class, message = "Name cannot be null for a book")
	@Size(min = 2, message = "Book name should have atleast 2 characters")
	private String name;

	@NotNull(groups = ServiceValidation.class, message = "Book cannot be added without specifying the name of the author")
	@AuthorName(groups = ServiceValidation.class, message = "You can add only books that are written by Pradeep!")
	private String author;

	public Book() {
		super();
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

	public interface ServiceValidation {
	}

	public interface ControllerValidation {
	}

	public interface UpdateBookValidation {

	}
}
