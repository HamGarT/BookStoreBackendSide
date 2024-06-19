package com.hamjo.bookstore.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuysService {

    public final BookService bookService;

    public double calcTotalprice(){
        Book book = bookService.getBook()
        return 0.0;
    }
}
