package com.backendjavatown.services;


import com.backendjavatown.models.Book;
import com.backendjavatown.models.Cd;
import com.backendjavatown.models.Dvd;
import com.backendjavatown.persistence.BookRepository;
import com.backendjavatown.persistence.CdRepository;
import com.backendjavatown.persistence.DvdRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final CdRepository cdRepository;
    private final DvdRepository dvdRepository;
    private final BookRepository bookRepository;

    public EmployeeService(CdRepository cdRepository, DvdRepository dvdRepository, BookRepository bookRepository) {
        this.cdRepository = cdRepository;
        this.dvdRepository = dvdRepository;
        this.bookRepository = bookRepository;
    }


    public void createCD(Cd cd) {
        try {
            cdRepository.save(cd);
            System.out.println("CD created successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createDVD(Dvd dvd) {
        try {
            dvdRepository.save(dvd);
            System.out.println("DVD created successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void creatBook(Book book) {
        try {
            bookRepository.save(book);
            System.out.println("Book created successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
