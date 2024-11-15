package com.herlandio7.stock.infra.controllers;

import java.math.BigDecimal;

public record CreateProductRequest(
    Long id,
    String name,
    String description,
    BigDecimal price,
    int stockQuantity,
    int criticalLevel
) {}