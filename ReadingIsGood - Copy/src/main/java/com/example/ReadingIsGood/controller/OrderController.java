package com.example.ReadingIsGood.controller;

import com.example.ReadingIsGood.dto.OrderDto;
import com.example.ReadingIsGood.payload.OrderRequest;
import com.example.ReadingIsGood.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Operation(summary = "Add Order Endpoint", description = "Create an order with given details ")
    @PostMapping
    public ResponseEntity<OrderDto> addOrder(@Valid @RequestBody OrderRequest request) {
        //bu id deki kitaptan bu kadar sipariş ver
        return ResponseEntity.ok(orderService.addOrder(request));
    }

    @Operation(summary = "Get Order By Id Endpoint", description = "Get order with given order ıd ")
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long orderId) {
        OrderDto orderDto = orderService.getOrderById(orderId);
        return ResponseEntity.ok(orderDto);
    }

    @Operation(summary = "Get Orders By DateRange Endpoint", description = "Get order by with given start date and end date ")
    @GetMapping("/date")
    public ResponseEntity<List<OrderDto>> getOrdersByDateRange(@RequestParam("startDate") String startDate,
                                                               @RequestParam("endDate") String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<OrderDto> orderDtos = orderService.getOrdersByDateInterval(start, end);
        return ResponseEntity.ok(orderDtos);
    }

}