package com.example.library.repository;

import com.example.library.model.BookReader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReaderRepository extends JpaRepository<BookReader, Long> {
}