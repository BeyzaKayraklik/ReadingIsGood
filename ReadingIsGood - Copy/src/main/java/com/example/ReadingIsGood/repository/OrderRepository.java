package com.example.ReadingIsGood.repository;

import com.example.ReadingIsGood.entity.Order;
import com.example.ReadingIsGood.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);

    List<Order> findByOrderDateBetween(LocalDate orderDate, LocalDate updatedDate);
    @Query("SELECT COUNT(o.id) FROM Order o")
    int getTotalOrderCount();
    @Query("SELECT COALESCE(SUM(o.totalPrice), 0) FROM Order o")
    double getTotalAmountOfPurchasedOrders();
//COALESCE fonk(kontrol edilecek değer, boşsa dönen default değer)
    List<Order> findByUser(User user);
//
//    Page<Order> findAllByCustomerId(Long customerId, Pageable pageable);

//    int count(); // Equivalent to getTotalOrderCount()
//
//    default double getTotalAmountOfPurchasedOrders() {
//        return findAll().stream()
//                .mapToDouble(Order::getTotalPrice)
//                .sum();
//    }
}
