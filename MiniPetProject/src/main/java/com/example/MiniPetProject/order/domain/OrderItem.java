package com.example.MiniPetProject.order.domain;

import com.example.MiniPetProject.order.domain.Order;
import com.example.MiniPetProject.product.domain.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "product name is required")
    @Size(min = 1, max = 100)
    private String productName;

    private BigDecimal price;

    @NotNull
    @Min(0)
    @Max(100)
    private int quantity;

    @NotNull
    private BigDecimal totalPrice;

    @PrePersist
    public void prePersist() {
        if (product != null && productName == null) {
            productName = product.getName();
        }
    }
}
