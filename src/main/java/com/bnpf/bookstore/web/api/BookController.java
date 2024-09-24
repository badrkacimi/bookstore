package com.bnpf.bookstore.web.api;

import com.bnpf.bookstore.service.BookService;
import com.bnpf.bookstore.web.dto.BookDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{title}")
    public ResponseEntity<List<BookDTO>> getBookById(@PathVariable String title) {
        List<BookDTO> books = bookService.searchBooksByTitle(title);
        return ResponseEntity.ok(books);
    }
}

