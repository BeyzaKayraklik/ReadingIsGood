package com.example.ReadingIsGood.controller;

import com.example.ReadingIsGood.dto.StatisticsDto;
import com.example.ReadingIsGood.repository.BookRepository;
import com.example.ReadingIsGood.repository.OrderRepository;
import com.example.ReadingIsGood.service.StatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistic")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private  OrderRepository orderRepository;
    @Autowired
    private  BookRepository bookRepository;

    @Operation(summary = "Get Statistic Endpoint", description ="Get all orders statistic : total order count , total amount of purchased orders , total count of purchased books ")
    @GetMapping
    public ResponseEntity<StatisticsDto> getStatistics() {
        int totalOrderCount = orderRepository.getTotalOrderCount();
        double totalAmountOfPurchasedOrders = orderRepository.getTotalAmountOfPurchasedOrders();
        int totalCountOfPurchasedBooks = bookRepository.getTotalCountOfPurchasedBooks();

        StatisticsDto statistics = new StatisticsDto(totalOrderCount, totalAmountOfPurchasedOrders, totalCountOfPurchasedBooks);
        return ResponseEntity.ok(statistics);
    }

}
