package com.herlandio7.stock.domain.entity;

import java.time.LocalDateTime;

public record Movement(
    Product product,
    int quantity,
    String type,
    LocalDateTime movementDate
) {}
