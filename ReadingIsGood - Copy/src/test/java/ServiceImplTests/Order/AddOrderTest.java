package ServiceImplTests.Order;

import com.example.ReadingIsGood.dto.OrderDto;
import com.example.ReadingIsGood.entity.Book;
import com.example.ReadingIsGood.entity.Order;
import com.example.ReadingIsGood.entity.User;
import com.example.ReadingIsGood.payload.OrderRequest;
import com.example.ReadingIsGood.repository.OrderRepository;
import com.example.ReadingIsGood.service.OrderService;
import com.example.ReadingIsGood.service.UserDetailReadingService;
import com.example.ReadingIsGood.service.serviceImpl.BookServiceImpl;
import com.example.ReadingIsGood.service.serviceImpl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {OrderService.class})
public class AddOrderTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserDetailReadingService userDetailReadingService;

    @Mock
    private BookServiceImpl bookService;

    @InjectMocks
    private OrderServiceImpl orderService;


    @Test
    public void testAddOrder() {
        // Mock authentication
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getName()).thenReturn("username");

        // Mock user
        User user = new User();
        when(userDetailReadingService.findByEmail("username")).thenReturn(Optional.of(user));

        // Mock book
        Book book = new Book();
        book.setPrice(10.0);
        book.setStock(25);
        when(bookService.getBookById(anyLong())).thenReturn(book);

        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        OrderRequest request = new OrderRequest();
        request.setBookId(1L);
        request.setQuantity(2);

        // Make the call
        OrderDto result = orderService.addOrder(request);

        // Verify the interactions
        verify(userDetailReadingService, times(1)).findByEmail("username");
        verify(bookService, times(1)).getBookById(1L);
        verify(orderRepository, times(1)).save(any(Order.class));

        assertNotNull(result);
        // Add more assertions if needed
    }
}
