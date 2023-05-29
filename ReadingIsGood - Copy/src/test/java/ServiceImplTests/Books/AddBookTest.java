package ServiceImplTests.Books;

import com.example.ReadingIsGood.dto.BookDto;
import com.example.ReadingIsGood.entity.Book;
import com.example.ReadingIsGood.payload.BookRequest;
import com.example.ReadingIsGood.repository.BookRepository;
import com.example.ReadingIsGood.service.BookService;
import com.example.ReadingIsGood.service.serviceImpl.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {BookService.class})
public class AddBookTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    public void addBookSuccess() {

        BookRequest request = new BookRequest();
        request.setPrice(9.99);
        request.setName("Sample Book");
        request.setStock(10);

        Book savedBook = new Book();
        savedBook.setId(1L);
        savedBook.setPrice(request.getPrice());
        savedBook.setName(request.getName());
        savedBook.setStock(request.getStock());
        // Mock the book object returned by the repository save method
        Book savedBookFromRepository = new Book();
        savedBookFromRepository.setId(savedBook.getId());
        savedBookFromRepository.setPrice(request.getPrice());
        savedBookFromRepository.setName(request.getName());
        savedBookFromRepository.setStock(request.getStock());
        // Mock the bookRepository.save() method to return the saved book

        when(bookRepository.save(any(Book.class))).thenReturn(savedBookFromRepository);

        BookDto result = bookService.addBook(request);
        // Verify the result
        assertEquals(1L, result.getId());
        assertEquals(savedBookFromRepository.getPrice(), result.getPrice());
        assertEquals(savedBookFromRepository.getName(), result.getName());
        assertEquals(savedBookFromRepository.getStock(), result.getStock());
    }





}
