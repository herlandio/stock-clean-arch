package com.herlandio7.stock.infra.controllers.dtos;

import java.math.BigDecimal;

public record ProductRequest(
    Long id,
    String name,
    String description,
    BigDecimal price,
    int stockQuantity,
    int criticalLevel
) {}