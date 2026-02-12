package com.example.MiniPetProject.product.domain;

import com.example.MiniPetProject.order.domain.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Table(name="products")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @Column(name="name")
    @NotNull
    @Size(min = 1, max = 100)
    private String name;

    @Column(name="description")
    @NotNull
    @Size(min = 1, max = 400)
    private String description;

    @Column(name="price")
    @NotNull
    private BigDecimal price;

    @Column(name = "created_at")
    @NotNull
    private Instant createdAt;

    @Column(name ="updated_at")
    @NotNull
    private Instant updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }
}
