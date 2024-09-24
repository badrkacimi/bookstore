package com.bnpf.bookstore.IT.mapper;

import com.bnpf.bookstore.IT.dto.OrderDTO;
import com.bnpf.bookstore.domain.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderMapper {
    @Mapping(source = "user.email", target = "email")
    OrderDTO toDto(Order order);
}
