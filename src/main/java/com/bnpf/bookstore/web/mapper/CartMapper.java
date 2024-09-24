package com.bnpf.bookstore.web.mapper;

import com.bnpf.bookstore.domain.entities.Cart;
import com.bnpf.bookstore.web.dto.CartDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CartItemMapper.class})
public interface CartMapper {
    @Mapping(source = "user.id", target = "userId")
    CartDTO toDto(Cart cart);
}
