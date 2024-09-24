package com.bnpf.bookstore.service;

import com.bnpf.bookstore.config.ValidationHelper;
import com.bnpf.bookstore.domain.entities.User;
import com.bnpf.bookstore.domain.repositories.UserRepository;
import com.bnpf.bookstore.web.dto.UserDTO;
import com.bnpf.bookstore.web.exceptions.InvalidRequestException;
import com.bnpf.bookstore.web.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDTO registerUser(UserDTO userDTO) {
        if (!ValidationHelper.isValidEmail(userDTO.email())) {
            throw new InvalidRequestException("Email or password is invalid");
        }
        if (userDTO.password().isEmpty()) {
            throw new InvalidRequestException("Password is invalid");
        }

        User existingUser = userRepository.findByEmail(userDTO.email());
        if (existingUser != null) {
            throw new InvalidRequestException("Username is already taken");
        }
        User user = userMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        log.info("user {} registered !", user.getEmail());
        return userMapper.toDto(user);
    }

}
