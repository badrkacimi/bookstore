package com.bnpf.bookstore.web.mapper;

import com.bnpf.bookstore.domain.entities.Book;
import com.bnpf.bookstore.web.dto.BookDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDTO toDto(Book book);

    Book toEntity(BookDTO bookDto);
}