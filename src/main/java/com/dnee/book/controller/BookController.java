package com.dnee.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.dnee.book.entity.Book;
import com.dnee.book.service.BookNotFoundException;
import com.dnee.book.service.BookService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class BookController {
    
    @Autowired
    private BookService bookService;

    @GetMapping("/books/page/{pageNo}")
    public String showBookList(
        @PathVariable (value = "pageNo") Integer pageNo,
        @RequestParam(defaultValue = "id") String sortField, 
        @RequestParam(defaultValue = "asc") String sortDir, // default sort by ascending order of id
        Model model){
        
        Integer pageSize = 10;
        Page<Book> bookPage = bookService.getPaginated(pageNo, pageSize, sortField, sortDir);
        List<Book> listBooks = bookPage.getContent();
        
		int totalBooks = (int) bookPage.getTotalElements();

		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
        model.addAttribute("listBooks", listBooks);
        model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", bookPage.getTotalPages());
		model.addAttribute("totalBooks", totalBooks);
        return "book";
    } 

    @GetMapping("/books")
	public String viewHomePage(Model model) {
		return showBookList(1, "id", "asc", model);		
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
