package com.bnpf.bookstore.web.mapper;

import com.bnpf.bookstore.domain.entities.CartItem;
import com.bnpf.bookstore.web.dto.CartItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "book.title", target = "bookTitle")
    CartItemDTO toDto(CartItem cartItem);
}