package com.herlandio7.stock.infra.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.herlandio7.stock.domain.entity.Product;

public class ProductDtoMapper {
    
    CreateProductResponse toResponse(Product product){
        return new CreateProductResponse(
            product.name(),
            product.description(),
            product.price(),
            product.stockQuantity()
        );
    }

    public Product toProduct(CreateProductRequest request) {
        return new Product(
            request.id(),
            request.name(),
            request.description(),
            request.price(),
            request.stockQuantity(),
            request.criticalLevel()
        );
    }

    List<CreateProductResponse> toResponseList(List<Product> productList) {
        return productList.stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
    }

    Optional<CreateProductResponse> toResponseById(Optional<Product> productById) {
        return productById.map(this::toResponse);
    }
}
