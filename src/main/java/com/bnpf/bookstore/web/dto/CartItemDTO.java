package com.bnpf.bookstore.web.dto;

public record CartItemDTO(Long bookId, String bookTitle, int quantity) {
}

