package com.example.ReadingIsGood.dto;

import com.example.ReadingIsGood.entity.Order;
import com.example.ReadingIsGood.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;

import java.time.LocalDate;

import static com.example.ReadingIsGood.enums.OrderStatus.PENDING;

@Data
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class OrderDto {
    private Long id;
    private UserDto user;
    private BookDto book;
    private Integer quantity;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate orderDate;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate updatedDate;
    private Double totalPrice;
//    private Integer total;

    private OrderStatus status = PENDING;

    // Constructors, getters, and setters


    public static OrderDto convertToOrder(Order order) {

        OrderDto dto = new OrderDto();
        dto.user = UserDto.convertToUser(order.getUser());
        dto.book = BookDto.convertToBook(order.getBook());
        dto.quantity = order.getQuantity();
        dto.totalPrice = order.getTotalPrice();
        dto.status = order.getStatus();
        dto.orderDate = order.getOrderDate();
        dto.updatedDate = order.getUpdatedDate();
        return dto;

    }



}
