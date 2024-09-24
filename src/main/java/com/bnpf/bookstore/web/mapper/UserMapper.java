package com.bnpf.bookstore.web.mapper;

import com.bnpf.bookstore.domain.entities.User;
import com.bnpf.bookstore.web.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDto(User user);

    User toEntity(UserDTO userDto);
}
