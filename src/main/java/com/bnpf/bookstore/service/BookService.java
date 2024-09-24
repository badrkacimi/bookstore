package com.bnpf.bookstore.service;

import com.bnpf.bookstore.IT.dto.BookDTO;
import com.bnpf.bookstore.IT.mapper.BookMapper;
import com.bnpf.bookstore.domain.entities.Book;
import com.bnpf.bookstore.domain.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<BookDTO> searchBooksByTitle(String title) {
        List<Book> books = bookRepository.findByTitleContainingIgnoreCase(title);
        return books.stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }
}

