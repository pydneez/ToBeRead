package com.dnee.book.repository;

import org.springframework.data.repository.CrudRepository;

import com.dnee.book.entity.Book;

public interface BookRepository extends CrudRepository<Book, Integer>{
    
}
