package com.bnpf.bookstore.web.api;

import com.bnpf.bookstore.service.CartService;
import com.bnpf.bookstore.web.dto.CartDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable Long userId) {
        CartDTO cartDTO = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cartDTO);
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<CartDTO> addItemToCart(@PathVariable Long userId, @RequestParam Long bookId, @RequestParam int quantity) {
        CartDTO cartDTO = cartService.addItemToCart(userId, bookId, quantity);
        return ResponseEntity.status(201).body(cartDTO);
    }

    @PutMapping("/{userId}/update")
    public ResponseEntity<CartDTO> updateCartItem(@PathVariable Long userId, @RequestParam Long bookId, @RequestParam int quantity) {
        CartDTO cartDTO = cartService.updateCartItem(userId, bookId, quantity);
        return ResponseEntity.ok(cartDTO);
    }

    @DeleteMapping("/{userId}/remove")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable Long userId, @RequestParam Long bookId) {
        cartService.removeItemFromCart(userId, bookId);
        return ResponseEntity.noContent().build();
    }
}