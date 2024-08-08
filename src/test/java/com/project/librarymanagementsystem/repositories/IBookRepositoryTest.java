package com.project.librarymanagementsystem.repositories;

import com.project.librarymanagementsystem.models.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class IBookRepositoryTest{
    @MockBean
    private IBookRepository bookRepository;
    public IBookRepositoryTest() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testFindById() {
        Book book = new Book(1L, "Book 1", "Author 1", "Publisher 1", "2020", "1234567890");
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Optional<Book> result = bookRepository.findById(1L);

        assertEquals(book, result.orElse(null));
    }
    @Test
    public void testSave() {
        Book book = new Book(1L, "Book 1", "Author 1", "Publisher 1", "2020", "1234567890");
        when(bookRepository.save(book)).thenReturn(book);

        Book result = bookRepository.save(book);

        assertEquals(book, result);
    }
}
