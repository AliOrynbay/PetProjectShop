package com.example.MiniPetProject.order.api;

import com.example.MiniPetProject.order.domain.OrderStatus;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemResponseDto {
    private Long id;
    private Long productId;
    private String productName;
    private BigDecimal price;
    private int quantity;
    private BigDecimal totalAmount;


}
