package com.hamjo.bookstore.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import lombok.ToString;

@Entity
@Table(name = "book")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;
    @Column(name ="title")
    private String title;
    @Column(name ="author")
    private String author;
    @Column(name ="synopsis")
    private String synopsis;
    @Column(name ="price")
    private double price;
    @Column(name ="amount")
    private int amount;
    @Column(name ="img")
    private String img;

}
