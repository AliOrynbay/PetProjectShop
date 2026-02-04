package com.example.MiniPetProject.order.api;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemRequestDto {
    private Long productId;
    private int quantity;
}
