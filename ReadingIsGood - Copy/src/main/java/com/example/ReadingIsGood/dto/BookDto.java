package com.example.ReadingIsGood.dto;

import com.example.ReadingIsGood.entity.Book;
import lombok.Data;

@Data
public class BookDto {
    private Long id;

    private String name;
    private int stock ;
    private boolean isActive = true;
    private Double price;



    public static BookDto convertToBook(Book book) {

        BookDto dto = new BookDto();
        dto.id = book.getId();
        dto.name = book.getName();
        dto.stock = book.getStock();
        dto.price = book.getPrice();
        return dto;

    }


}
