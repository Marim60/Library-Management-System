package com.project.librarymanagementsystem.repositories;

import com.project.librarymanagementsystem.models.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPatronRepository extends JpaRepository<Patron, Long> {
    Optional<Patron> findByEmail(String email);
}
