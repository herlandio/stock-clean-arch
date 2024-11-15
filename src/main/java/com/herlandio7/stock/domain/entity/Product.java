package com.herlandio7.stock.domain.entity;

import java.math.BigDecimal;

public record Product(
	Long id,
	String name,
	String description,
	BigDecimal price,
	int stockQuantity,
	int criticalLevel
) {}
