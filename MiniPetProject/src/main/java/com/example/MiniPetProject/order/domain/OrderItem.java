package com.example.MiniPetProject.order.domain;

import com.example.MiniPetProject.order.domain.Order;
import com.example.MiniPetProject.product.domain.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private String productName; // <-- добавляем поле

    private BigDecimal price;
    private int quantity;
    private BigDecimal totalPrice;

    @PrePersist
    public void prePersist() {
        if (product != null && productName == null) {
            productName = product.getName();
        }
    }
}
