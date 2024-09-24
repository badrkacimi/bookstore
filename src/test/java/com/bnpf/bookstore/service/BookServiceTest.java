package com.bnpf.bookstore.service;

import com.bnpf.bookstore.IT.dto.BookDTO;
import com.bnpf.bookstore.IT.mapper.BookMapper;
import com.bnpf.bookstore.domain.entities.Book;
import com.bnpf.bookstore.domain.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    BookRepository bookRepository;
    @Mock
    BookMapper bookMapper;
    @InjectMocks
    BookService bookService;

    @Test
    public void test_get_all_books() {
        List<Book> books = List.of(new Book(1L, "Title1", "Author1", 10.0, "isbn1", LocalDateTime.now(), null),
                new Book(2L, "Title2", "Author2", 15.0, "isbn2", LocalDateTime.now(), null));

        when(bookRepository.findAll()).thenReturn(books);
        when(bookMapper.toDto(any(Book.class))).thenAnswer(invocation -> {
            Book book = invocation.getArgument(0);
            return new BookDTO(book.getTitle(), book.getAuthor(), book.getIsbn(), book.getPrice());
        });

        List<BookDTO> result = bookService.getAllBooks();

        assertEquals(2, result.size());
        assertEquals("Title1", result.get(0).title());
        assertEquals("Title2", result.get(1).title());
    }

    @Test
    public void test_search_books_byTitle() {
        List<Book> books = List.
                of(new Book(1L, "Title1", "Author1", 10.0, "isbn1", LocalDateTime.now(), null));

        when(bookRepository.findByTitleContainingIgnoreCase(anyString())).thenReturn(books);
        when(bookMapper.toDto(any(Book.class))).thenAnswer(invocation -> {
            Book book = invocation.getArgument(0);
            return new BookDTO(book.getTitle(), book.getAuthor(), book.getIsbn(), book.getPrice());
        });

        List<BookDTO> result = bookService.searchBooksByTitle("Title1");

        assertEquals(1, result.size());
        assertEquals("Title1", result.getFirst().title());
    }
}