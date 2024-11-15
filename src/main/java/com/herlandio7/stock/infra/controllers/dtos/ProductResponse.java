package com.herlandio7.stock.infra.controllers.dtos;

import java.math.BigDecimal;

public record ProductResponse (
  	String name,
	String description,
	BigDecimal price,
	int stockQuantity
) {}
