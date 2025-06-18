package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.model.BookReader;
import com.example.library.model.Reader;
import com.example.library.service.BookReaderService;
import com.example.library.service.BookService;
import com.example.library.service.ReaderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryController {
    private final BookService bookService;
    private final ReaderService readerService;
    private final BookReaderService bookReaderService;

    public LibraryController(BookService bookService, ReaderService readerService, BookReaderService bookReaderService) {
        this.bookService = bookService;
        this.readerService = readerService;
        this.bookReaderService = bookReaderService;
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @GetMapping("/readers")
    public List<Reader> getAllReaders() {
        return readerService.findAll();
    }

    @PostMapping("/readers")
    public Reader addReader(@RequestBody Reader reader) {
        return readerService.save(reader);
    }

    @PostMapping("/borrow")
    public BookReader borrowBook(@RequestParam Long bookId, @RequestParam Long readerId) {
        Book book = bookService.findById(bookId);
        Reader reader = readerService.findById(readerId);
        return bookReaderService.borrowBook(book, reader);
    }

    @PostMapping("/return/{id}")
    public BookReader returnBook(@PathVariable Long id) {
        BookReader bookReader = bookReaderService.findAll().stream()
                .filter(br -> br.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (bookReader != null) {
            return bookReaderService.returnBook(bookReader);
        }
        return null;
    }

    @GetMapping("/borrows")
    public List<BookReader> getAllBorrows() {
        return bookReaderService.findAll();
    }
}