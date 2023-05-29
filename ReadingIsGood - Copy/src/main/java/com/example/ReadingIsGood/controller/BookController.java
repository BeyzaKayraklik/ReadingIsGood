package com.example.ReadingIsGood.controller;

import com.example.ReadingIsGood.dto.BookDto;
import com.example.ReadingIsGood.entity.Book;
import com.example.ReadingIsGood.payload.BookRequest;
import com.example.ReadingIsGood.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @Operation(summary = "Create Book Endpoint", description ="Create a new book with given details ")
    @PostMapping
    public ResponseEntity<BookDto> addBook(@Valid @RequestBody BookRequest request) {
        return ResponseEntity.ok(bookService.addBook(request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Book By Id Endpoint", description = "Get a new book with given book ıd ")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }
    @Operation(summary = "Update Book Stock Endpoint", description ="Update a book stock with given book id  ")
    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBookStock(@PathVariable("id") Long id, @Valid @RequestBody BookRequest request) {
        bookService.updateBookStock(id, request.getStock());
        return ResponseEntity.ok().build();
    }
    @Operation(summary = "Get All Book Endpoint", description ="Get all books  ")
    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBook() {
        return ResponseEntity.ok(bookService.getAll());
    }

}
