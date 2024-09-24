package com.bnpf.bookstore.domain.repositories;


import com.bnpf.bookstore.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
