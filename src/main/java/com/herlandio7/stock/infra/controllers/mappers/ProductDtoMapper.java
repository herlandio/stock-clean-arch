package com.herlandio7.stock.infra.controllers.mappers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.herlandio7.stock.domain.entity.Product;
import com.herlandio7.stock.infra.controllers.dtos.ProductRequest;
import com.herlandio7.stock.infra.controllers.dtos.ProductResponse;

public class ProductDtoMapper {
    
    public ProductResponse toResponse(Product product){
        return new ProductResponse(
            product.name(),
            product.description(),
            product.price(),
            product.stockQuantity()
        );
    }

    public Product toProduct(ProductRequest request) {
        return new Product(
            request.id(),
            request.name(),
            request.description(),
            request.price(),
            request.stockQuantity(),
            request.criticalLevel()
        );
    }

    public List<ProductResponse> toResponseList(List<Product> productList) {
        return productList.stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
    }

    public Optional<ProductResponse> toResponseById(Optional<Product> productById) {
        return productById.map(this::toResponse);
    }
}
