package com.herlandio7.stock.infra.controllers;

import java.math.BigDecimal;

public record CreateProductResponse (
  	String name,
	String description,
	BigDecimal price,
	int stockQuantity
) {}
