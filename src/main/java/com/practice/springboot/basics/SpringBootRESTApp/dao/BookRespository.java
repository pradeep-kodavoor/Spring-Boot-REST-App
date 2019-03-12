package com.practice.springboot.basics.SpringBootRESTApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.springboot.basics.SpringBootRESTApp.domain.Book;

@Repository
public interface BookRespository extends JpaRepository<Book, Long> {

}
