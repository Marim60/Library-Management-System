package com.project.librarymanagementsystem.controllers;

import com.project.librarymanagementsystem.models.Patron;
import com.project.librarymanagementsystem.responses.ErrorResponse;
import com.project.librarymanagementsystem.services.PatronService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PatronControllerTest {

    @Mock
    private PatronService patronService;

    @InjectMocks
    private PatronController patronController;

    public PatronControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPatrons() {
        List<Patron> patrons = List.of(new Patron(1L, "John Doe","USA new york street 9" ,"01158620735","johndoe@example.com", "password"));
        when(patronService.getAllPatrons()).thenReturn(patrons);

        ResponseEntity<List<Patron>> response = patronController.getAllPatrons();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patrons, response.getBody());
    }

    @Test
    public void testGetPatronById_PatronFound() {
        Patron patron = new Patron(1L, "John Doe","USA new york street 9" ,"01158620735","johndoe@example.com", "password");
        when(patronService.getPatronById(1L)).thenReturn(patron);

        ResponseEntity<?> response = patronController.getPatronById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patron, response.getBody());
    }

    @Test
    public void testGetPatronById_PatronNotFound() {
        when(patronService.getPatronById(1L)).thenReturn(null);

        ResponseEntity<?> response = patronController.getPatronById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Patron not found", ((ErrorResponse) response.getBody()).getErrors().get(0));
    }

    @Test
    public void testAddPatron() {
        Patron patron = new Patron(1L, "John Doe","USA new york street 9" ,"01158620735","johndoe@example.com", "password");
        when(patronService.addPatron(patron)).thenReturn(patron);

        ResponseEntity<Patron> response = patronController.addPatron(patron);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patron, response.getBody());
    }

    @Test
    public void testUpdatePatron_PatronFound() {
        Patron patron = new Patron(1L, "John Doe","USA new york street 9" ,"01158620735","johndoe@example.com", "password");
        when(patronService.getPatronById(1L)).thenReturn(patron);
        when(patronService.updatePatron(1L, patron)).thenReturn(patron);

        ResponseEntity<?> response = patronController.updatePatron(1L, patron);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patron, response.getBody());
    }

    @Test
    public void testUpdatePatron_PatronNotFound() {
        when(patronService.getPatronById(1L)).thenReturn(null);

        ResponseEntity<?> response = patronController.updatePatron(1L, new Patron());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Patron not found", ((ErrorResponse) response.getBody()).getErrors().get(0));
    }

    @Test
    public void testDeletePatron() {
        doNothing().when(patronService).deletePatron(1L);

        ResponseEntity<Void> response = patronController.deletePatron(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(patronService, times(1)).deletePatron(1L);
    }
}
