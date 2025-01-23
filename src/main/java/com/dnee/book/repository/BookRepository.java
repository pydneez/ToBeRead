package com.dnee.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dnee.book.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{
    // out-of-the box methods are defined here by default
}
