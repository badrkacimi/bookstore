package com.bnpf.bookstore.web.dto;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(String email, LocalDateTime orderAt, List<OrderItemDTO> items, double totalAmount) {
}

