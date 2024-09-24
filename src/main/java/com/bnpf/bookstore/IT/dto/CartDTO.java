package com.bnpf.bookstore.IT.dto;

import java.util.List;

public record CartDTO(Long userId, List<CartItemDTO> items) {
}

