package com.example.MiniPetProject.order.repository;

import com.example.MiniPetProject.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findOrderById(Long id);
}
