package com.herlandio7.stock.infra.controllers;

import java.time.LocalDateTime;

import com.herlandio7.stock.domain.entity.Product;

public record CreateMovementRequest(
    Product product,
    int quantity,
    String type,
    LocalDateTime movementDate
) {}
