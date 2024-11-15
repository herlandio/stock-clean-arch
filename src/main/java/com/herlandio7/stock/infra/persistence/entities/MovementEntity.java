package com.herlandio7.stock.infra.persistence.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movement")
public class MovementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ProductEntity product;
    
    private int quantity;
    private String type;
    private LocalDateTime movementDate = LocalDateTime.now();

    public MovementEntity(
        ProductEntity product,
        int quantity,
        String type,
        LocalDateTime movementDate
    ) {
        this.product = product;
        this.quantity = quantity;
        this.type = type;
        this.movementDate = movementDate;
    }
}
