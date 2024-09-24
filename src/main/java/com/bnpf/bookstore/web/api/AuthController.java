package com.bnpf.bookstore.web.api;

import com.bnpf.bookstore.service.UserService;
import com.bnpf.bookstore.web.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.registerUser(userDTO);
        String message = MessageFormat.format("User {0} successfully created !", createdUser.email());
        return ResponseEntity.status(201).body(message);
    }
}
