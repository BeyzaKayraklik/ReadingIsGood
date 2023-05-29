package com.example.ReadingIsGood.entity;

import com.example.ReadingIsGood.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static com.example.ReadingIsGood.enums.OrderStatus.PENDING;

@Entity
@Table(name = "order_detail")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @NotNull
    private Integer quantity;

    @CreationTimestamp
    private LocalDate orderDate;

    @UpdateTimestamp
    private LocalDate updatedDate;

    @NotNull
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = PENDING ;

}
