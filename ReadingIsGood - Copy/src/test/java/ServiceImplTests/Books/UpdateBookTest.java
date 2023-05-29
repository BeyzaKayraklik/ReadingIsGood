package ServiceImplTests.Books;

import com.example.ReadingIsGood.dto.BookDto;
import com.example.ReadingIsGood.entity.Book;
import com.example.ReadingIsGood.repository.BookRepository;
import com.example.ReadingIsGood.service.BookService;
import com.example.ReadingIsGood.service.serviceImpl.BookServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest(classes = {BookService.class})
public class UpdateBookTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void givenValidIdAndQuantity_whenUpdateBookStock_thenReturnUpdatedBook() {
        Long bookId = 1L;
        Integer quantity = 10;
        Book book = new Book();
        book.setId(bookId);
        book.setStock(5);

        Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        Mockito.when(bookRepository.save(Mockito.any(Book.class))).then(AdditionalAnswers.returnsFirstArg());

        // when
        BookDto updatedBook = bookService.updateBookStock(bookId, quantity);

        // then
        Mockito.verify(bookRepository, Mockito.times(1)).findById(bookId);
        Mockito.verify(bookRepository, Mockito.times(1)).save(Mockito.any(Book.class));
        Assertions.assertEquals(bookId, updatedBook.getId());
        Assertions.assertEquals(quantity, updatedBook.getStock());
    }

    @Test
    void givenInvalidId_whenUpdateBookStock_thenThrowIllegalStateException() {
        // given
        Long bookId = 1L;
        Integer quantity = 10;
        Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.empty());
        Assertions.assertThrows(IllegalStateException.class, () -> {
            bookService.updateBookStock(bookId, quantity);
        });
        Mockito.verify(bookRepository, Mockito.times(1)).findById(bookId);
        Mockito.verify(bookRepository, Mockito.never()).save(Mockito.any(Book.class));
    }
    @Test
    void givenNegativeQuantity_whenUpdateBookStock_thenThrowIllegalStateException() {
        Long bookId = 1L;
        Integer quantity = -1;

        Book book = new Book();
        book.setId(bookId);
        book.setStock(5);

        Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        Assertions.assertThrows(IllegalStateException.class, () -> {
            bookService.updateBookStock(bookId, quantity);
        });

        Mockito.verify(bookRepository, Mockito.times(1)).findById(bookId);
        Mockito.verify(bookRepository, Mockito.never()).save(Mockito.any(Book.class));
    }

    @Test
    void givenZeroQuantity_whenUpdateBookStock_thenThrowIllegalStateException() {
        Long bookId = 1L;
        Integer quantity = 0;
        Book book = new Book();
        book.setId(bookId);
        book.setStock(5);

        Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        Assertions.assertThrows(IllegalStateException.class, () -> {
            bookService.updateBookStock(bookId, quantity);
        });

        Mockito.verify(bookRepository, Mockito.times(1)).findById(bookId);
        Mockito.verify(bookRepository, Mockito.never()).save(Mockito.any(Book.class));
    }
}
