package com.bnpf.bookstore.IT.mapper;

import com.bnpf.bookstore.IT.dto.CartDTO;
import com.bnpf.bookstore.domain.entities.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CartItemMapper.class})
public interface CartMapper {
    @Mapping(source = "user.id", target = "userId")
    CartDTO toDto(Cart cart);
}
