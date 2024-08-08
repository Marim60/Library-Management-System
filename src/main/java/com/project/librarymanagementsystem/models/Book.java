package com.project.librarymanagementsystem.models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data

@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    @Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "Title must contain only letters, numbers, and spaces")
    private String title;
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Author must contain only letters and spaces")
    private String author;
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Publisher must contain only letters and spaces")
    private String publisher;
    @Pattern(regexp = "^\\d{4}$", message = "Publication year must be a 4-digit number")
    private String publicationYear;
    @Pattern(regexp = "^[0-9]{13}$", message = "ISBN must be a 13-digit number")
    private String isbn;
    public Book(Long bookId) {
        this.id = bookId;
    }

}
