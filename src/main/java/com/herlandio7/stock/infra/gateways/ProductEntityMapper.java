package com.herlandio7.stock.infra.gateways;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.herlandio7.stock.domain.entity.Product;
import com.herlandio7.stock.infra.persistence.ProductEntity;

public class ProductEntityMapper {
    
    ProductEntity toEntity(Product productDomainObj) {
        return new ProductEntity(
            productDomainObj.id(),
            productDomainObj.name(),
            productDomainObj.description(),
            productDomainObj.price(),
            productDomainObj.stockQuantity(),
            productDomainObj.criticalLevel()
        );
    }

    Product toDomain(ProductEntity productEntity) {
        return new Product(
            productEntity.getId(),
            productEntity.getName(),
            productEntity.getDescription(),
            productEntity.getPrice(),
            productEntity.getStockQuantity(),
            productEntity.getCriticalLevel()
        );
    }

    Optional<Product> toDomainById(Optional<ProductEntity> productEntity) {
        return productEntity.map(this::toDomain);
    }

    List<Product> toDomainList(List<ProductEntity> productEntities) {
        return productEntities.stream()
            .map(this::toDomain)
            .collect(Collectors.toList());
    }
}
