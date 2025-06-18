package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.model.BookReader;
import com.example.library.model.Reader;
import com.example.library.repository.BookReaderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookReaderService {
    private final BookReaderRepository bookReaderRepository;

    public BookReaderService(BookReaderRepository bookReaderRepository) {
        this.bookReaderRepository = bookReaderRepository;
    }

    public BookReader borrowBook(Book book, Reader reader) {
        BookReader bookReader = new BookReader(book, reader, LocalDate.now());
        return bookReaderRepository.save(bookReader);
    }

    public BookReader returnBook(BookReader bookReader) {
        bookReader.setReturnDate(LocalDate.now());
        return bookReaderRepository.save(bookReader);
    }

    public List<BookReader> findAll() {
        return bookReaderRepository.findAll();
    }
}