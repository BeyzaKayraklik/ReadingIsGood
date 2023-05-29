package com.example.ReadingIsGood.payload;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BookRequest {

	@NotEmpty(message = "Book name cannot be empty")
	private String name;

	@NotNull(message = "stock is required")
	@PositiveOrZero(message = "stock cannot be less than 0")
	private int stock;

	@NotNull(message = "price is required")
	@PositiveOrZero(message = "price cannot be less than 0")
	private Double price;


//    private boolean isActive = true;
}
