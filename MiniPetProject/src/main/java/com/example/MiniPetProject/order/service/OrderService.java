package com.example.MiniPetProject.order.service;

import com.example.MiniPetProject.order.api.*;
import com.example.MiniPetProject.order.domain.Order;
import com.example.MiniPetProject.order.domain.OrderItem;
import com.example.MiniPetProject.order.domain.OrderStatus;
import com.example.MiniPetProject.order.repository.OrderRepository;
import com.example.MiniPetProject.product.domain.Product;
import com.example.MiniPetProject.product.service.ProductService;
import com.example.MiniPetProject.user.api.UserResponseDto;
import com.example.MiniPetProject.user.domain.User;
import com.example.MiniPetProject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final UserService userService;

    private final OrderMapper orderMapper;

    public List<OrderResponseDto> getOrders(){
        List<Order> orders = orderRepository.findAll();
        log.info("Orders found: {}", orders);
        return orders.stream()
                .map(orderMapper::toOrderResponseDto)
                .collect(Collectors.toList());
    }

    @Cacheable(value = "orders" , key = "#id")
    public OrderDetailsResponseDto getOrderById(int id){
        log.info("Getting order by id: {}", id);
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        log.info("Order found: {}", order);
        return orderMapper.toOrderDetailsResponseDto(order);
    }

    @Transactional
    @CacheEvict(value = "orders" , key = "#result.id")
    public OrderDetailsResponseDto createOrder(OrderRequestDto dto) {

        log.info("Received OrderRequestDto: {}", dto);

        if (dto.getUserId() == null) {
            log.error("User ID is null in OrderRequestDto!");
            throw new RuntimeException("User ID cannot be null");
        }

        User user = userService.findUserById1(dto.getUserId());

        log.info("User found: {}", user);

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.CREATED);
        order.setCreatedAt(Instant.now());

        BigDecimal orderTotalPrice = BigDecimal.ZERO;

        for (OrderItemRequestDto itemDto : dto.getOrderItemRequestDtoList()) {
            Product product = productService.findProductById(itemDto.getProductId());
            if (product == null) throw new RuntimeException("Product not found!");

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setPrice(product.getPrice());
            orderItem.setQuantity(itemDto.getQuantity());
            orderItem.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(itemDto.getQuantity())));

            order.getItems().add(orderItem);
            orderTotalPrice = orderTotalPrice.add(orderItem.getTotalPrice());
        }

        order.setTotalAmount(orderTotalPrice);
        orderRepository.save(order);


        log.info("Order saved successfully with ID: {}", order.getId());

        return orderMapper.toOrderDetailsResponseDto(order);
    }

    public void deactivateOrderById(int id){
        log.info("Deactivating order by id: {}", id);
        Order foundOrder = orderRepository.findById(id).orElseThrow();
        log.info("Order found: {}", foundOrder);
        foundOrder.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(foundOrder);
        log.info("Order deactivated successfully with ID: {}", foundOrder.getId());
    }

}
