package com.bnpf.bookstore.IT.mapper;

import com.bnpf.bookstore.IT.dto.BookDTO;
import com.bnpf.bookstore.domain.entities.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDTO toDto(Book book);

    Book toEntity(BookDTO bookDto);
}