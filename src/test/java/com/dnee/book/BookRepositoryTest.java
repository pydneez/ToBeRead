package com.dnee.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.dnee.book.entity.Book;
import com.dnee.book.entity.Genre;
import com.dnee.book.entity.Status;
import com.dnee.book.repository.BookRepository;

// test class
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // use the actual database instead of default inmemory databasex
@Rollback(false) // keep data committed to database
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;


    @Test
    public void testAddNew(){
        // create a new book
        Book book3 = new Book();
        book3.setTitle("White nights");
        book3.setAuthor("Fyodor Dostoevsky");
        book3.setGenre(Genre.LITERARY);
        book3.setStatus(Status.WANT_TO_READ);
        book3.setDateStarted(LocalDate.now());
        book3.setDateFinished(null);
        
        Book savedBook = bookRepository.save(book3);
        assertNotNull(savedBook);
        System.out.println(savedBook);
    }

    @Test
    public void testListAll(){
        Iterable<Book> books = bookRepository.findAll();
        
        for (Book book : books){
            System.out.println(book);
        }
    }

    @Test
    public void testUpdate(){
        Integer bookId = 1;
        // find by id return Optional, we use get() to get the actual object 
        Book book = bookRepository.findById(bookId).get(); 
        book.setDateFinished(LocalDate.now());
        book.setStatus(Status.READ);
        bookRepository.save(book);

        Book updatedBook = bookRepository.findById(bookId).get();
        System.out.println(updatedBook);
        assertEquals(updatedBook.getStatus(), Status.READ);
        assertNotNull(updatedBook.getDateFinished());
    }

    @Test
    public void testGet(){
        Integer bookId = 2;
        Optional<Book> book = bookRepository.findById(bookId);
        assertNotNull(book);
        System.out.println(book.get());
    }

    @Test
    public void testDelete(){
        Integer bookId = 2;
        bookRepository.deleteById(bookId);
        Optional<Book> book = bookRepository.findById(bookId);
        assertEquals(book.isEmpty(), true);
    }
}
