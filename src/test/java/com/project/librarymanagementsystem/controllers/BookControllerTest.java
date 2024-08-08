package com.project.librarymanagementsystem.controllers;

import com.project.librarymanagementsystem.models.Book;
import com.project.librarymanagementsystem.responses.ErrorResponse;
import com.project.librarymanagementsystem.services.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    public BookControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBooks() {
        List<Book> books = List.of(new Book(1L, "Book 1", "Author 1", "Publisher 1", "2020", "1234567890"));
        when(bookService.getAllBooks()).thenReturn(books);

        ResponseEntity<List<Book>> response = bookController.getAllBooks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(books, response.getBody());
    }

    @Test
    public void testGetBookById_BookFound() {
        Book book = new Book(1L, "Book 1", "Author 1", "Publisher 1", "2020", "1234567890");
        when(bookService.getBookById(1L)).thenReturn(book);

        ResponseEntity<?> response = bookController.getBookById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(book, response.getBody());
    }

    @Test
    public void testGetBookById_BookNotFound() {
        when(bookService.getBookById(1L)).thenReturn(null);

        ResponseEntity<?> response = bookController.getBookById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Book not found", ((ErrorResponse) response.getBody()).getErrors().get(0));
    }

    @Test
    public void testAddBook() {
        Book book = new Book(1L, "Book 1", "Author 1", "Publisher 1", "2020", "1234567890");
        when(bookService.addBook(book)).thenReturn(book);

        ResponseEntity<Book> response = bookController.addBook(book);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(book, response.getBody());
    }

    @Test
    public void testUpdateBook_BookFound() {
        Book book = new Book(1L, "Book 1", "Author 1", "Publisher 1", "2020", "1234567890");
        when(bookService.getBookById(1L)).thenReturn(book);
        when(bookService.updateBook(1L, book)).thenReturn(book);

        ResponseEntity<?> response = bookController.updateBook(1L, book);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(book, response.getBody());
    }

    @Test
    public void testUpdateBook_BookNotFound() {
        when(bookService.getBookById(1L)).thenReturn(null);

        ResponseEntity<?> response = bookController.updateBook(1L, new Book());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Book not found", ((ErrorResponse) response.getBody()).getErrors().get(0));
    }

    @Test
    public void testDeleteBook() {
        doNothing().when(bookService).deleteBook(1L);

        ResponseEntity<Void> response = bookController.deleteBook(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(bookService, times(1)).deleteBook(1L);
    }
}
