package com.project.librarymanagementsystem.services;

import com.project.librarymanagementsystem.models.Patron;
import com.project.librarymanagementsystem.repositories.IPatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatronService {
    @Autowired
    private IPatronRepository patronRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    public Patron getPatronById(Long id) {
        return patronRepository.findById(id).orElse(null);
    }

    public Patron addPatron(Patron patron) {
        patron.setPassword(bCryptPasswordEncoder.encode(patron.getPassword()));
        return patronRepository.save(patron);
    }

    public Patron updatePatron(Long id, Patron patron) {
        Patron existingPatron = patronRepository.findById(id).orElse(null);
        if (existingPatron == null) {
            return null;
        }
        existingPatron.setName(patron.getName());
        existingPatron.setAddress(patron.getAddress());
        existingPatron.setPhoneNumber(patron.getPhoneNumber());
        existingPatron.setPassword(bCryptPasswordEncoder.encode(patron.getPassword()));
        return patronRepository.save(existingPatron);
    }

    public void deletePatron(Long id) {
        patronRepository.deleteById(id);
    }
}
