package com.herlandio7.stock.application.gateways;

import java.util.List;
import java.util.Optional;

import com.herlandio7.stock.domain.entity.Product;

public interface IProductGateway {
    Product addProduct(Product product);
    Product updateProduct(Long id, Product updatedProduct);
    List<Product> listProducts();
    Optional<Product> getProductById(Long id);
    void deleteProduct(Long id);
}
