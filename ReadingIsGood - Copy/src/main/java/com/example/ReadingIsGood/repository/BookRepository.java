package com.example.ReadingIsGood.repository;

import com.example.ReadingIsGood.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>{
    @Query("SELECT COALESCE(SUM(b.stock), 0) FROM Book b")
    int getTotalCountOfPurchasedBooks();
//    default int getTotalCountOfPurchasedBooks() {
//        return findAll().stream()
//                .mapToInt(Book::getStock)
//                .sum();
//    }

}
