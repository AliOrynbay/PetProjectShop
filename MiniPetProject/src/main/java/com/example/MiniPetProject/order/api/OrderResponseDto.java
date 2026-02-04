package com.example.MiniPetProject.order.api;

import com.example.MiniPetProject.order.domain.OrderItem;
import com.example.MiniPetProject.order.domain.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class OrderResponseDto {
    private Long id;
    private OrderStatus status;
    private BigDecimal totalAmount;
    private Instant createdAt;
}
