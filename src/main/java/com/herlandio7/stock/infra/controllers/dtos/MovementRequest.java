package com.herlandio7.stock.infra.controllers.dtos;

import java.time.LocalDateTime;

import com.herlandio7.stock.domain.entity.Product;

public record MovementRequest(
    Product product,
    int quantity,
    String type,
    LocalDateTime movementDate
) {}
