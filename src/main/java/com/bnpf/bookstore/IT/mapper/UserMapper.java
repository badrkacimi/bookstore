package com.bnpf.bookstore.IT.mapper;

import com.bnpf.bookstore.IT.dto.UserDTO;
import com.bnpf.bookstore.domain.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDto(User user);

    User toEntity(UserDTO userDto);
}
