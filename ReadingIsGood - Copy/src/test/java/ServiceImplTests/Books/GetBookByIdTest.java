package ServiceImplTests.Books;

import com.example.ReadingIsGood.dto.BookDto;
import com.example.ReadingIsGood.entity.Book;
import com.example.ReadingIsGood.repository.BookRepository;
import com.example.ReadingIsGood.service.BookService;
import com.example.ReadingIsGood.service.serviceImpl.BookServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {BookService.class})

public class GetBookByIdTest {


    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private BookDto bookDto;

    @Test
    public void findBookDetailById_WithValidId_ReturnsBookDetailDTO() {
        Long id = 1L;
        Book bookDetail = new Book();
        bookDetail.setId(id);
        bookDetail.setName("name");
        bookDetail.setStock(45);
        bookDetail.setPrice(25.0);

        when(bookRepository.findById(id)).thenReturn(Optional.of(bookDetail));

        BookDto result = BookDto.convertToBook(bookService.getBookById(id));

        // assert
        assertNotNull(result);
        assertEquals(bookDetail.getId(), result.getId());
        assertEquals(bookDetail.getPrice(), result.getPrice());
        assertEquals(bookDetail.getStock(), result.getStock());
        assertEquals(bookDetail.getName(), result.getName());

        verify(bookRepository, times(1)).findById(id);
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void findBookDetailById_WithInValidId_ThrownException() {
        Long id = 1L;
        when(bookRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalStateException.class, () -> {
            bookService.getBookById(id);
        });

        verify(bookRepository, times(1)).findById(id);
        verifyNoMoreInteractions(bookRepository);
    }


}
