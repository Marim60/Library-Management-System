package com.project.librarymanagementsystem.services;

import com.project.librarymanagementsystem.models.Book;
import com.project.librarymanagementsystem.repositories.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private IBookRepository bookRepository;
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }
    public Book updateBook(Long id,Book book) {
        Book existingBook = bookRepository.findById(id).orElse(null);
        existingBook.setAuthor(book.getAuthor());
        existingBook.setTitle(book.getTitle());
        existingBook.setPublisher(book.getPublisher());
        existingBook.setPublicationYear(book.getPublicationYear());
        existingBook.setIsbn(book.getIsbn());
        return bookRepository.save(existingBook);
    }
    public void deleteBook(Long id) {

        bookRepository.deleteById(id);
    }
}
