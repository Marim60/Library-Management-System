package com.project.librarymanagementsystem.controllers;

import com.project.librarymanagementsystem.models.BorrowingRecord;
import com.project.librarymanagementsystem.models.Patron;
import com.project.librarymanagementsystem.models.Book;
import com.project.librarymanagementsystem.responses.ErrorResponse;
import com.project.librarymanagementsystem.services.BorrowingRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BorrowingRecordControllerTest {

    @Mock
    private BorrowingRecordService borrowingRecordService;

    @InjectMocks
    private BorrowingRecordController borrowingRecordController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBorrowBook() {
        Long bookId = 1L;
        Long patronId = 1L;

        Patron patron = new Patron();
        patron.setId(patronId);

        Book book = new Book();
        book.setId(bookId);

        LocalDate borrowingDate = LocalDate.now();
        LocalDate returnDate = borrowingDate.plusDays(7);

        BorrowingRecord borrowingRecord = new BorrowingRecord(1L, patron, book, borrowingDate, returnDate);

        when(borrowingRecordService.borrowBook(bookId, patronId)).thenReturn(borrowingRecord);

        ResponseEntity<BorrowingRecord> response = borrowingRecordController.borrowBook(bookId, patronId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(borrowingRecord, response.getBody());
    }

    @Test
    public void testReturnBook_RecordFound() {
        Long bookId = 1L;
        Long patronId = 1L;

        Patron patron = new Patron();
        patron.setId(patronId);

        Book book = new Book();
        book.setId(bookId);

        LocalDate borrowingDate = LocalDate.now();
        LocalDate returnDate = borrowingDate.plusDays(7);

        BorrowingRecord borrowingRecord = new BorrowingRecord(1L, patron, book, borrowingDate, returnDate);

        when(borrowingRecordService.returnBook(bookId, patronId)).thenReturn(borrowingRecord);

        ResponseEntity<?> response = borrowingRecordController.returnBook(bookId, patronId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(borrowingRecord, response.getBody());
    }

    @Test
    public void testReturnBook_RecordNotFound() {
        Long bookId = 1L;
        Long patronId = 1L;

        when(borrowingRecordService.returnBook(bookId, patronId)).thenReturn(null);

        ResponseEntity<?> response = borrowingRecordController.returnBook(bookId, patronId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Borrowing record not found", ((ErrorResponse) response.getBody()).getErrors().get(0));
    }
}
