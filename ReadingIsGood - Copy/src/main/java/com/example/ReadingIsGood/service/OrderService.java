package com.example.ReadingIsGood.service;

import com.example.ReadingIsGood.dto.OrderDto;
import com.example.ReadingIsGood.entity.Order;
import com.example.ReadingIsGood.payload.OrderRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderDto addOrder(OrderRequest request);



    OrderDto getOrderById(Long orderId);

    List<OrderDto> getOrdersByDateInterval(LocalDate startDate, LocalDate endDate);



}
