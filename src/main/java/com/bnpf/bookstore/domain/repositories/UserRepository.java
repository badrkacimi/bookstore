package com.bnpf.bookstore.domain.repositories;

import com.bnpf.bookstore.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
