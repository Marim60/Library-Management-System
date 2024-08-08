package com.project.librarymanagementsystem.controllers;

import com.project.librarymanagementsystem.models.Book;
import com.project.librarymanagementsystem.responses.ErrorResponse;
import com.project.librarymanagementsystem.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        if(bookService.getBookById(id) == null)
        {
            ErrorResponse response = new ErrorResponse();
            response.setErrors(List.of("Book not found"));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(bookService.getBookById(id));
    }
    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
        return ResponseEntity.ok(bookService.addBook(book));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id,@Valid @RequestBody Book book) {
        if(bookService.getBookById(id) == null)
        {
            ErrorResponse response = new ErrorResponse();
            response.setErrors(List.of("Book not found"));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(bookService.updateBook(id,book));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }

}
