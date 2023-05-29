package com.example.ReadingIsGood.service.serviceImpl;

import com.example.ReadingIsGood.dto.BookDto;
import com.example.ReadingIsGood.entity.Book;
import com.example.ReadingIsGood.payload.BookRequest;
import com.example.ReadingIsGood.repository.BookRepository;
import com.example.ReadingIsGood.service.BookService;
import com.example.ReadingIsGood.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
	// book :add new book
	// : update book stock
	@Autowired
	BookRepository bookRepository;

	@Override
	public BookDto addBook(BookRequest request) {

		Book book = new Book();
		book.setPrice(request.getPrice());
		book.setName(request.getName());
		book.setStock(request.getStock());
		Book bookdb = bookRepository.save(book);
		LogUtil.info("Book created successfully with id: ", bookdb.getId());
		return BookDto.convertToBook(bookdb);
	}

	@Override
	public Book getBookById(Long id) {
		Book book = bookRepository.findById(id).orElseThrow(()
				-> new IllegalStateException("Book not found"));
		LogUtil.info("The book with this id has been brought: ", id);
		return book;
	}

	@Override
	public BookDto updateBookStock(Long id, Integer quantity) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		if(optionalBook.isPresent() ) {
			Book book = optionalBook.get();
			if(book.getStock() > 0 && quantity > 0) {
				book.setStock(quantity);
				Book updateBook = bookRepository.save(book);

				LogUtil.info("Book stock updated with id : ", id);

				return BookDto.convertToBook(updateBook);
			}else throw new IllegalStateException("invalid values entered");
		}else throw new IllegalStateException("there is no book in this id ");
	}

	@Override
	public BookDto updateBookStockForOrder(Long id, Integer quantity) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		if (optionalBook.isPresent()) {
			Book book = optionalBook.get();
			if (book.getStock() > quantity) {

				book.setStock(book.getStock() - quantity);
				Book updateBook = bookRepository.save(book);
				LogUtil.info("Book stock updated for order with id : ", id);

				return BookDto.convertToBook(updateBook);
			} else
				throw new IllegalStateException("stock can not minus ");
		} else
			throw new IllegalStateException("there is no book in this id");
	}

	// pagination
	@Override
	public List<BookDto> getAll() {
		List<Book> book = bookRepository.findAll();
		List<BookDto> bookDtos = new ArrayList<>();
		book.forEach(bookL -> bookDtos.add(BookDto.convertToBook(bookL)));
		LogUtil.info(" All books brought ");

		return bookDtos;
	}
}
