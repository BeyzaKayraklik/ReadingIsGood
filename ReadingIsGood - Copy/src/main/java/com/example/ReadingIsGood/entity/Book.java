package com.example.ReadingIsGood.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "books")
@Getter
@Setter

public class Book {
    @Id
    @GeneratedValue(generator = "seq_book", strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer stock ;

    @Column(nullable = false)
    private Double price;

}
