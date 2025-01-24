package com.dnee.book.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dnee.book.entity.Book;
import com.dnee.book.repository.BookRepository;

// addtional logic and validation is applied here
@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;
    
    public List<Book> listAll(){
        return (List<Book>) bookRepository.findAll(); 
    }

    public void save(Book book){
        bookRepository.save(book);
    }

    public void delete(Integer id) throws BookNotFoundException{
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (! bookOptional.isPresent()){
            throw new BookNotFoundException("Could not find the book by the ID : " + id);
        }

        bookRepository.deleteById(id);
       
    }

    public Book get(Integer id) throws BookNotFoundException{
        Optional<Book> bookOptional = bookRepository.findById(id);

        if (bookOptional.isPresent()){
            return bookOptional.get();
        }

        throw new BookNotFoundException("Could not find the book by the ID : " + id);
    }

    public Page<Book> getPaginated(Integer pageNo, Integer pageSize, String sortField, String sortDir){
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        return this.bookRepository.findAll(pageable);

    }

    
}

