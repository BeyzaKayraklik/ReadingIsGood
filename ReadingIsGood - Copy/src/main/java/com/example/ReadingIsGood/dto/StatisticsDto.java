package com.example.ReadingIsGood.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsDto {
    private int totalOrderCount;
    private double totalAmountOfPurchasedOrders;
    private int totalCountOfPurchasedBooks;
}
