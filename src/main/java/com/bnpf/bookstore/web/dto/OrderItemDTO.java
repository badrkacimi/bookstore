package com.bnpf.bookstore.web.dto;

public record OrderItemDTO(Long bookId, String bookTitle, int quantity, double price) {
}

