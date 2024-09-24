package com.bnpf.bookstore.IT.mapper;

import com.bnpf.bookstore.IT.dto.CartItemDTO;
import com.bnpf.bookstore.domain.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "book.title", target = "bookTitle")
    CartItemDTO toDto(CartItem cartItem);
}