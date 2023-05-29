package com.example.ReadingIsGood.service.serviceImpl;

import com.example.ReadingIsGood.config.JwtService;
import com.example.ReadingIsGood.dto.OrderDto;
import com.example.ReadingIsGood.entity.Book;
import com.example.ReadingIsGood.entity.Order;
import com.example.ReadingIsGood.entity.User;
import com.example.ReadingIsGood.payload.OrderRequest;
import com.example.ReadingIsGood.repository.OrderRepository;
import com.example.ReadingIsGood.service.BookService;
import com.example.ReadingIsGood.service.OrderService;
import com.example.ReadingIsGood.service.UserDetailReadingService;
import com.example.ReadingIsGood.util.LogUtil;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserDetailReadingService userDetailReadingService;
    @Autowired
    BookService bookService;
    @Autowired
    JwtService jwtService;
    @Autowired
    private EntityManager entityManager;
    @Override
    public OrderDto addOrder(OrderRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            Order order = new Order();
            System.out.println(order.getId());
            User user = userDetailReadingService.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found for order service"));
             order.setUser(user);
             Book book = bookService.getBookById(request.getBookId());
             order.setBook(book);
             order.setTotalPrice(request.getQuantity() * book.getPrice());
             order.setQuantity(request.getQuantity());
             order.setStatus(order.getStatus());
             System.out.println(book.getPrice());
                 bookService.updateBookStockForOrder(request.getBookId(), request.getQuantity());
            Order savedOrder = orderRepository.save(order);
            LogUtil.info("New order added with id: ", savedOrder.getId());

            return OrderDto.convertToOrder(savedOrder);
        } else {
            throw new UsernameNotFoundException("Authentication is required to place an order!");
        }
    }
    @Override
    public OrderDto getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        LogUtil.info("The order with this id has been brought:",orderId);

        return OrderDto.convertToOrder(order);
    }

    @Override
    public List<OrderDto> getOrdersByDateInterval(LocalDate startDate, LocalDate endDate) {
        List<Order> orders = orderRepository.findByOrderDateBetween(startDate, endDate);
        List<OrderDto> orderDtos = new ArrayList<>();
        orders.forEach(orderList -> orderDtos.add(OrderDto.convertToOrder(orderList)));
        LogUtil.info("All orders between these dates have been brought:",startDate,endDate);

        return orderDtos;
    }

}
