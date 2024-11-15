package com.herlandio7.stock.infra.controllers;

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
}
