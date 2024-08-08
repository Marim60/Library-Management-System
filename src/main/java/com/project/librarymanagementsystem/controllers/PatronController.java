package com.project.librarymanagementsystem.controllers;

import com.project.librarymanagementsystem.models.Patron;
import com.project.librarymanagementsystem.responses.ErrorResponse;
import com.project.librarymanagementsystem.services.PatronService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {
    @Autowired
    private PatronService patronService;

    @GetMapping
    public ResponseEntity<List<Patron>> getAllPatrons() {
        return ResponseEntity.ok(patronService.getAllPatrons());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getPatronById(@PathVariable Long id) {
        if(patronService.getPatronById(id) == null)
        {
            ErrorResponse response = new ErrorResponse();
            response.setErrors(List.of("Patron not found"));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(patronService.getPatronById(id));
    }
    @PostMapping
    public ResponseEntity<Patron> addPatron(@Valid @RequestBody Patron patron) {
        return ResponseEntity.ok(patronService.addPatron(patron));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatron(@PathVariable Long id,@Valid @RequestBody Patron patron) {
        if(patronService.getPatronById(id) == null)
        {
            ErrorResponse response = new ErrorResponse();
            response.setErrors(List.of("Patron not found"));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(patronService.updatePatron(id,patron));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id) {
        patronService.deletePatron(id);
        return ResponseEntity.ok().build();
    }

}
