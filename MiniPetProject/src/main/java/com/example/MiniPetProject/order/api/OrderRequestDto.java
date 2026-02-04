package com.example.MiniPetProject.order.api;

import com.example.MiniPetProject.user.api.UserResponseDto;
import com.example.MiniPetProject.user.domain.User;
import jakarta.persistence.PrePersist;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDto {
    private List<OrderItemRequestDto> orderItemRequestDtoList = new ArrayList<>();
    private Long userId;
}
