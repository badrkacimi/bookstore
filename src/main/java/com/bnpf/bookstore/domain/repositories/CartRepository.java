package com.bnpf.bookstore.domain.repositories;

import com.bnpf.bookstore.domain.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long userId);
}