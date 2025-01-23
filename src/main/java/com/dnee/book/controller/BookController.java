package com.dnee.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.dnee.book.entity.Book;
import com.dnee.book.service.BookNotFoundException;
import com.dnee.book.service.BookService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class BookController {
    
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public String showBookList(
        @RequestParam(defaultValue = "id") String sortField, 
        @RequestParam(defaultValue = "asc") String sortDir, // default sort by ascending order of id
        Model model){

        List<Book> listBooks = bookService.getAllBooks(sortField, sortDir);
        model.addAttribute("listBooks", listBooks);
        return "book";
    } 

    @GetMapping("/books/new")
    public String newBookEntry(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("pageTitle", "Add New Book");
        return "book-form";
    }

    @PostMapping("/books/save")
    public String saveBook(Book book, RedirectAttributes ra) {
        bookService.save(book);
        ra.addFlashAttribute("message", "The Book Has Been Saved To Your Library Successfully");
        return "redirect:/books";
    }

    @GetMapping("/books/edit/{id}")
    public String updateBook(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            Book book = bookService.get(id);
            model.addAttribute("book", book);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");
            return "book-form";
        } catch (BookNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/books";
        }
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {
            bookService.delete(id);
        } catch (BookNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
        }
       
        return "redirect:/books";
    }
    
    
}
