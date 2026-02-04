package com.example.MiniPetProject.order.api;


import com.example.MiniPetProject.order.domain.OrderStatus;
import com.example.MiniPetProject.user.api.UserResponseDto;
import jakarta.persistence.PrePersist;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailsResponseDto {
    private Long id;
    private OrderStatus status;
    private BigDecimal totalAmount;
    private Instant createdAt;
    private UserResponseDto user;
    private List<OrderItemResponseDto> orderItems;
}
