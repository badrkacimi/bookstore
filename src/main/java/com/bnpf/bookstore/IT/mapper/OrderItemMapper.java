package com.bnpf.bookstore.IT.mapper;

import com.bnpf.bookstore.IT.dto.OrderItemDTO;
import com.bnpf.bookstore.domain.entities.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "book.title", target = "bookTitle")
    OrderItemDTO toDto(OrderItem orderItem);
}
