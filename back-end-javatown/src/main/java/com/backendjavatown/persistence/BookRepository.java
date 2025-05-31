package com.backendjavatown.persistence;

import com.backendjavatown.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
