package com.bnpf.bookstore.IT.dto;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(String email, LocalDateTime orderAt, List<OrderItemDTO> items, double totalAmount) {
}

