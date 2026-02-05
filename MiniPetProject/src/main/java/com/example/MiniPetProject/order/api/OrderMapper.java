package com.example.MiniPetProject.order.api;

import com.example.MiniPetProject.order.api.OrderDetailsResponseDto;
import com.example.MiniPetProject.order.api.OrderItemResponseDto;
import com.example.MiniPetProject.order.api.OrderResponseDto;
import com.example.MiniPetProject.order.domain.Order;
import com.example.MiniPetProject.order.domain.OrderItem;
import com.example.MiniPetProject.user.api.UserResponseDto;
import com.example.MiniPetProject.user.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "user", source = "user")
    @Mapping(target = "orderItems", source = "items")
    OrderDetailsResponseDto toOrderDetailsResponseDto(Order order);

    OrderResponseDto toOrderResponseDto(Order order);

    UserResponseDto toUserResponseDto(User user);

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productName", source = "product.name")
    @Mapping(target = "totalAmount", source = "totalPrice") 
    OrderItemResponseDto toOrderItemResponseDto(OrderItem orderItem);
}
