package com.bnpf.bookstore.service;

import com.bnpf.bookstore.domain.entities.Book;
import com.bnpf.bookstore.domain.entities.Cart;
import com.bnpf.bookstore.domain.entities.CartItem;
import com.bnpf.bookstore.domain.entities.User;
import com.bnpf.bookstore.domain.repositories.BookRepository;
import com.bnpf.bookstore.domain.repositories.CartRepository;
import com.bnpf.bookstore.web.dto.CartDTO;
import com.bnpf.bookstore.web.mapper.CartMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {
    @Mock
    CartRepository cartRepository;
    @Mock
    BookRepository bookRepository;
    @Mock
    CartMapper cartMapper;
    @InjectMocks
    CartService cartService;

    @Test
    public void add_new_item_to_existing_cart() {
        int quantity = 2;

        User user = new User();
        user.setId(1L);
        user.setEmail("badr@gmail.com");

        Book book = new Book();
        book.setId(1L);
        book.setAuthor("Badr Kacimi");

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setItems(new ArrayList<>());

        when(cartRepository.findByUserId(user.getId())).thenReturn(cart);
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        when(cartMapper.toDto(any(Cart.class))).thenReturn(new CartDTO(user.getId(), new ArrayList<>()));

        CartDTO result = cartService.addItemToCart(user.getId(), book.getId(), quantity);

        assertNotNull(result);
        verify(cartRepository).save(cart);

    }

    @Test
    public void remove_item_from_cart() {
        Long userId = 1L;
        Long bookId = 1L;

        Cart cart = new Cart();
        cart.setUser(new User());

        CartItem cartItem = new CartItem();
        cartItem.setBook(new Book());
        cartItem.getBook().setId(bookId);
        cart.getItems().add(cartItem);

        when(cartRepository.findByUserId(userId)).thenReturn(cart);

        cartService.removeItemFromCart(userId, bookId);

        verify(cartRepository).save(cart);
        assertTrue(cart.getItems().isEmpty());
    }
}