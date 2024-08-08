package com.project.librarymanagementsystem.services;

import com.project.librarymanagementsystem.models.Book;
import com.project.librarymanagementsystem.models.BorrowingRecord;
import com.project.librarymanagementsystem.models.Patron;
import com.project.librarymanagementsystem.repositories.IBorrowingRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowingRecordService {
    @Autowired
    private IBorrowingRecordRepository borrowingRecordRepository;
    public BorrowingRecord borrowBook(long bookId, long patronId) {
        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(new Book(bookId));
        borrowingRecord.setPatron(new Patron(patronId));
        borrowingRecord.setBorrowingDate(LocalDate.now());
        return borrowingRecordRepository.save(borrowingRecord);
    }
    public BorrowingRecord returnBook(Long bookId, Long patronId) {
        Optional<BorrowingRecord> recordOptional = borrowingRecordRepository.findByBookIdAndPatronId(bookId, patronId);
        if (recordOptional.isPresent()) {
            BorrowingRecord record = recordOptional.get();
            record.setReturnDate(LocalDate.now());
            return borrowingRecordRepository.save(record);
        } else {
            return null;
        }
    }

}
