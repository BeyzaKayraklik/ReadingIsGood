package com.example.ReadingIsGood.payload;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class OrderRequest {

    @Positive(message = "bookId must be greater than 0")
    private Long bookId;

    @Positive(message = "quantity  must be greater than 0")
    private Integer quantity;
}
