package com.project.librarymanagementsystem.controllers;

import com.project.librarymanagementsystem.models.BorrowingRecord;
import com.project.librarymanagementsystem.responses.ErrorResponse;
import com.project.librarymanagementsystem.services.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BorrowingRecordController {
    @Autowired
    private BorrowingRecordService borrowingRecordService;
    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecord> borrowBook(@PathVariable long bookId, @PathVariable long patronId) {

        return ResponseEntity.ok(borrowingRecordService.borrowBook(bookId,patronId));
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<?> returnBook(@PathVariable long bookId, @PathVariable long patronId) {
        if(borrowingRecordService.returnBook(bookId,patronId) == null)
        {
            {
                ErrorResponse response = new ErrorResponse();
                response.setErrors(List.of("Borrowing record not found"));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        }
        return ResponseEntity.ok(borrowingRecordService.returnBook(bookId,patronId));
    }


}
