package com.bnpf.bookstore.IT;

import com.bnpf.bookstore.domain.entities.*;
import com.bnpf.bookstore.domain.repositories.CartRepository;
import com.bnpf.bookstore.domain.repositories.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @MockBean
    CartRepository cartRepository;
    @MockBean
    OrderRepository orderRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "badr@example.com", password = "password123")
    public void test_place_Order_successfully() throws Exception {
        //Mock Cart
        User user = new User();
        user.setEmail("badr@example.com");
        user.setId(1L);

        Book book = new Book(1L, "Title1", "Author1", 10.0, "isbn1", LocalDateTime.now(), null);
        Cart cart = new Cart(1L, user, new ArrayList<>());
        cart.getItems().add(new CartItem(1L, cart, book, 10));

        when(cartRepository.findByUserId(user.getId())).thenReturn(cart);
        when(orderRepository.save(ArgumentMatchers.any())).thenReturn(new Order());
        when(cartRepository.save(ArgumentMatchers.any())).thenReturn(cart);

        mockMvc.perform(MockMvcRequestBuilders.post("/orders/{userId}/place", user.getId()))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json("{\"email\":\"badr@example.com\"}"));
    }
}