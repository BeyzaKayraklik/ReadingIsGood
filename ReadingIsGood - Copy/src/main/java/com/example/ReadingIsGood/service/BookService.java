package com.example.ReadingIsGood.service;

import com.example.ReadingIsGood.dto.BookDto;
import com.example.ReadingIsGood.entity.Book;
import com.example.ReadingIsGood.payload.BookRequest;

import java.util.List;

public interface BookService {
	BookDto addBook(BookRequest request);

	Book getBookById(Long id);

	BookDto updateBookStockForOrder(Long id, Integer quantity);

	List<BookDto> getAll();

	BookDto updateBookStock(Long id, Integer quantity);

}
