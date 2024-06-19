package com.hamjo.bookstore.service;
import com.hamjo.bookstore.repository.BookRepository;
import com.hamjo.bookstore.model.Book;
import com.hamjo.bookstore.model.User;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final UserService userService;
    @Value("${upload.dir}")
    private String uploadDir;

    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> getBooksByPrice(double price){ return  bookRepository.findByPrice(price);}

    public List<Book> searchBooksByTitle(String keyword) {
        return bookRepository.findByTitle(keyword);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book addBook(MultipartFile file, String title, String author, String synopsis, double price, int amount) throws SQLException, IOException {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setSynopsis(synopsis);
        book.setPrice(price);
        book.setAmount(amount);
        // Save the image to the specified directory
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path uploadPath = Paths.get(System.getProperty("user.dir") + uploadDir); // Adjust path to be absolute
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);
        book.setImg(fileName); // Save the file name to the database

        return bookRepository.save(book);
    }

    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    public void decrementBookItem(Long cartItemId, Integer amount) {
        User user = userService.getUser();
        Book book = book.getCart();
        if (Objects.isNull(cart) || Objects.isNull(cart.getCartItemList()) || cart.getCartItemList().isEmpty()) {
            throw new ResourceNotFoundException("Empty cart");
        }

        CartItem cartItem = cart.getCartItemList()
                .stream()
                .filter(ci -> ci.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("CartItem not found"));


        if (book.getAmount() <= amount) {
            List<CartItem> cartItemList = cart.getCartItemList();
            cartItemList.remove(cartItem);
            if (Objects.isNull(cart.getCartItemList()) || cart.getCartItemList().isEmpty()) {
                user.setCart(null);
                userService.saveUser(user);
                return null;
            }
            cart.setCartItemList(cartItemList);
            cart = calculatePrice(cart);
            cart = cartRepository.save(cart);
            return cartResponseConverter.apply(cart);
        }

        cartItem.setAmount(cartItem.getAmount() - amount);
        cart = calculatePrice(cart);
        cart = cartRepository.save(cart);
        return cartResponseConverter.apply(cart);
    }
}
