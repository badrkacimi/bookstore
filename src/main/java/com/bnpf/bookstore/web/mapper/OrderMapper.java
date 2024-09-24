package com.bnpf.bookstore.web.mapper;

import com.bnpf.bookstore.domain.entities.Order;
import com.bnpf.bookstore.web.dto.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderMapper {
    @Mapping(source = "user.email", target = "email")
    OrderDTO toDto(Order order);
}
