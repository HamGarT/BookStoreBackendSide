package com.hamjo.bookstore.repository;
import com.hamjo.bookstore.model.Buys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuysRepository extends JpaRepository<Buys, Integer> {
}
