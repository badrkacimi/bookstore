package com.bnpf.bookstore.service;

import com.bnpf.bookstore.domain.entities.Book;
import com.bnpf.bookstore.domain.entities.Cart;
import com.bnpf.bookstore.domain.entities.CartItem;
import com.bnpf.bookstore.domain.entities.User;
import com.bnpf.bookstore.domain.repositories.BookRepository;
import com.bnpf.bookstore.domain.repositories.CartRepository;
import com.bnpf.bookstore.domain.repositories.UserRepository;
import com.bnpf.bookstore.web.dto.CartDTO;
import com.bnpf.bookstore.web.exceptions.InvalidRequestException;
import com.bnpf.bookstore.web.exceptions.ResourceNotFoundException;
import com.bnpf.bookstore.web.mapper.CartMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final CartMapper cartMapper;

    public CartDTO getCartByUserId(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            throw new ResourceNotFoundException("Cart not found for user id " + userId);
        }
        return cartMapper.toDto(cart);
    }

    @Transactional
    public CartDTO addItemToCart(Long userId, Long bookId, int quantity) {
        log.info("adding Items to Cart...");

        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
            cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
        }

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + bookId));

        List<CartItem> items = cart.getItems();

        Optional<CartItem> existingCartItem = items.stream()
                .filter(item -> item.getBook().getId().equals(bookId))
                .findFirst();

        if (existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            CartItem newCartItem = new CartItem();
            newCartItem.setBook(book);
            newCartItem.setCart(cart);
            newCartItem.setQuantity(quantity);
            items.add(newCartItem);
        }

        cartRepository.save(cart);
        log.info("Item {} added to Cart", bookId);
        return cartMapper.toDto(cart);
    }

    @Transactional
    public CartDTO updateCartItem(Long userId, Long bookId, int quantity) {
        log.info("updating Items in Cart...");

        if (quantity < 0) {
            throw new InvalidRequestException("Quantity cannot be negative.");
        }
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            throw new ResourceNotFoundException("Cart not found for user id " + userId);
        }

        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getBook().getId().equals(bookId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found for book id " + bookId));

        if (quantity > 0) {
            cartItem.setQuantity(quantity);
        } else {
            cart.getItems().remove(cartItem);
        }

        cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }

    @Transactional
    public void removeItemFromCart(Long userId, Long bookId) {
        log.info("remove Items in Cart...");

        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            throw new ResourceNotFoundException("Cart not found for user id " + userId);
        }

        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getBook().getId().equals(bookId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found for book id " + bookId));

        cart.getItems().remove(cartItem);
        cartRepository.save(cart);
        log.info("Item {} removed from Cart", bookId);

    }
}

