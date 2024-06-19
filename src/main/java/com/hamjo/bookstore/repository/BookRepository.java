package com.hamjo.bookstore.repository;
import com.hamjo.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    List<Book> findByAuthor(String author);
    List<Book> findByTitle(String keyword);
    List<Book> findByPrice(double price);

}
