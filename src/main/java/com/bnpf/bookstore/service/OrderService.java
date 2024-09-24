package com.bnpf.bookstore.service;

import com.bnpf.bookstore.domain.entities.Cart;
import com.bnpf.bookstore.domain.entities.Order;
import com.bnpf.bookstore.domain.entities.OrderItem;
import com.bnpf.bookstore.domain.repositories.CartRepository;
import com.bnpf.bookstore.domain.repositories.OrderRepository;
import com.bnpf.bookstore.web.dto.OrderDTO;
import com.bnpf.bookstore.web.exceptions.ResourceNotFoundException;
import com.bnpf.bookstore.web.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final OrderMapper orderMapper;

    public OrderDTO placeOrder(Long userId) {
        log.info("placing order...");

        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null || cart.getItems().isEmpty()) {
            throw new ResourceNotFoundException("Cart is empty or not found for user id " + userId);
        }

        Order order = new Order();
        order.setUser(cart.getUser());
        order.setOrderAt(LocalDateTime.now());
        order.setItems(cart.getItems().stream().map(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getBook().getPrice());
            return orderItem;
        }).collect(Collectors.toList()));

        double totalAmount = order.getItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        order.setTotalAmount(totalAmount);

        orderRepository.save(order);

        // Clear the cart
        cart.getItems().clear();
        cartRepository.save(cart);

        log.info("order of user {} placed", userId);
        return orderMapper.toDto(order);
    }
}
