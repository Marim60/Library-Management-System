package com.project.librarymanagementsystem.services;

import com.project.librarymanagementsystem.models.Book;
import com.project.librarymanagementsystem.repositories.IBookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookServiceTest {

    @Mock
    private IBookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    public BookServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBooks() {
        List<Book> books = List.of(new Book(1L, "Book 1", "Author 1", "Publisher 1", "2020", "1234567890"));
        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.getAllBooks();

        assertEquals(books, result);
    }

    @Test
    public void testGetBookById_BookFound() {
        Book book = new Book(1L, "Book 1", "Author 1", "Publisher 1", "2020", "1234567890");
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Book result = bookService.getBookById(1L);

        assertEquals(book, result);
    }

    @Test
    public void testGetBookById_BookNotFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        Book result = bookService.getBookById(1L);

        assertEquals(null, result);
    }

    @Test
    public void testAddBook() {
        Book book = new Book(1L, "Book 1", "Author 1", "Publisher 1", "2020", "1234567890");
        when(bookRepository.save(book)).thenReturn(book);

        Book result = bookService.addBook(book);

        assertEquals(book, result);
    }

    @Test
    public void testUpdateBook() {
        Book existingBook = new Book(1L, "Book 1", "Author 1", "Publisher 1", "2020", "1234567890");
        Book updatedBook = new Book(1L, "Updated Book 1", "Updated Author 1", "Updated Publisher 1", "2021", "0987654321");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(existingBook)).thenReturn(updatedBook);

        Book result = bookService.updateBook(1L, updatedBook);

        assertEquals(updatedBook.getTitle(), result.getTitle());
        assertEquals(updatedBook.getAuthor(), result.getAuthor());
        assertEquals(updatedBook.getPublisher(), result.getPublisher());
        assertEquals(updatedBook.getPublicationYear(), result.getPublicationYear());
        assertEquals(updatedBook.getIsbn(), result.getIsbn());
    }

    @Test
    public void testDeleteBook() {
        doNothing().when(bookRepository).deleteById(1L);

        bookService.deleteBook(1L);

        verify(bookRepository, times(1)).deleteById(1L);
    }
}
