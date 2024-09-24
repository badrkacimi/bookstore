package com.bnpf.bookstore.service;

import com.bnpf.bookstore.IT.dto.OrderDTO;
import com.bnpf.bookstore.IT.dto.OrderItemDTO;
import com.bnpf.bookstore.IT.mapper.OrderMapper;
import com.bnpf.bookstore.domain.entities.*;
import com.bnpf.bookstore.domain.repositories.CartRepository;
import com.bnpf.bookstore.domain.repositories.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    OrderRepository orderRepository;
    @Mock
    CartRepository cartRepository;
    @Mock
    OrderMapper orderMapper;
    @InjectMocks
    OrderService orderService;

    @Test
    public void place_oder_successfully() {
        User user = new User();
        user.setId(1L);
        user.setEmail("badr@capgemini.com");

        Cart cart = new Cart();
        cart.setUser(user);

        List<CartItem> cartItems = new ArrayList<>();
        CartItem cartItem = new CartItem();
        Book book = new Book();
        book.setPrice(20.0);
        cartItem.setBook(book);
        cartItem.setQuantity(2);
        cartItems.add(cartItem);
        cart.setItems(cartItems);

        List<OrderItemDTO> orderItems = new ArrayList<>();
        orderItems.add(new OrderItemDTO(1L, cartItem.getBook().getTitle(), cartItem.getQuantity(), cartItem.getBook().getPrice()));

        when(cartRepository.findByUserId(user.getId())).thenReturn(cart);
        when(orderMapper.toDto(ArgumentMatchers.any(Order.class))).thenReturn(new OrderDTO(user.getEmail(), LocalDateTime.now(), orderItems, 40.0));

        OrderDTO result = orderService.placeOrder(user.getId());

        assertNotNull(result);
        assertEquals(result.email(), user.getEmail());
        assertEquals(result.totalAmount(), 40.0);
        verify(orderRepository, times(1)).save(any());
        verify(cartRepository, times(1)).save(cart);  // Verify that the cart is cleared
    }

}