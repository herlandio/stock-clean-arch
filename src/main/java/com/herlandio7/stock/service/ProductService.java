package com.herlandio7.stock.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.herlandio7.stock.model.Product;
import com.herlandio7.stock.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;

    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        return productRepository.findById(id).map(product -> {
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
            product.setStockQuantity(updatedProduct.getStockQuantity());
            product.setCriticalLevel(updatedProduct.getCriticalLevel());
            return productRepository.save(product);
        }).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public void checkCriticalStock() {
        productRepository.findAll().forEach(product -> {
            if (product.getStockQuantity() <= product.getCriticalLevel()) {
                sendLowStockNotification(product);
            }
        });
    }

    private void sendLowStockNotification(Product product) {
        logger.warn("Notification: Low stock for product: {}", product.getName());
        // Notification logic (email, SMS, etc.) would go here
    }
}
