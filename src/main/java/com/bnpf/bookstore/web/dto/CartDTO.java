package com.bnpf.bookstore.web.dto;

import java.util.List;

public record CartDTO(Long userId, List<CartItemDTO> items) {
}

