package com.project.librarymanagementsystem.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Name must contain only letters and spaces")
   private String name;
    @Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "Address must contain only letters, numbers, and spaces")
    private String address;
    @Pattern(regexp = "^[0-9]{11}$", message = "Phone number must be a 11-digit number")
    private String phoneNumber;
    @Pattern(regexp = "^[A-Za-z0-9]+@[A-Za-z0-9]+\\.[A-Za-z0-9]+$", message = "Email must be a valid email address")
    private String email;
    private String password;
    public Patron(Long patronId) {
        this.id = patronId;
    }
}
