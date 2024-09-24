package com.bnpf.bookstore.IT;

import com.bnpf.bookstore.IT.dto.OrderDTO;
import com.bnpf.bookstore.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/{userId}/place")
    public ResponseEntity<OrderDTO> placeOrder(@PathVariable Long userId) {
        OrderDTO orderDTO = orderService.placeOrder(userId);
        return ResponseEntity.status(201).body(orderDTO);
    }
}