package com.bnpf.bookstore.web.mapper;

import com.bnpf.bookstore.domain.entities.OrderItem;
import com.bnpf.bookstore.web.dto.OrderItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "book.title", target = "bookTitle")
    OrderItemDTO toDto(OrderItem orderItem);
}
