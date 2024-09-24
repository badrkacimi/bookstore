package com.bnpf.bookstore.IT.dto;

public record OrderItemDTO(Long bookId, String bookTitle, int quantity, double price) {
}

