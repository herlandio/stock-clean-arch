package com.herlandio7.stock.infra.gateways;

import java.util.List;
import java.util.Optional;

import com.herlandio7.stock.application.gateways.IProductGateway;
import com.herlandio7.stock.domain.entity.Product;
import com.herlandio7.stock.infra.persistence.ProductEntity;
import com.herlandio7.stock.infra.persistence.ProductRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductRepositoryGateway implements IProductGateway {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepositoryGateway.class);
    private final ProductRepository productRepository;
    private final ProductEntityMapper productEntityMapper;

    @Override
    public Product addProduct(Product productDomainObj) {
        ProductEntity productEntity = productEntityMapper.toEntity(productDomainObj);
        ProductEntity saveProduct = productRepository.save(productEntity);
        return productEntityMapper.toDomain(saveProduct);
    }

    @Override
    public Product updateProduct(Long id, Product updatedProduct) {
        ProductEntity productEntity = productEntityMapper.toEntity(updatedProduct);
        return productRepository.findById(id).map(product -> {
            product.setName(productEntity.getName());
            product.setDescription(productEntity.getDescription());
            product.setPrice(productEntity.getPrice());
            product.setStockQuantity(productEntity.getStockQuantity());
            product.setCriticalLevel(productEntity.getCriticalLevel());
            ProductEntity updateProduct = productRepository.save(product);
            return productEntityMapper.toDomain(updateProduct);
        }).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public List<Product> listProducts() {
        List<ProductEntity> updateProduct = productRepository.findAll();
        return productEntityMapper.toDomainList(updateProduct);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        Optional<ProductEntity> productId = productRepository.findById(id);
        return productEntityMapper.toDomainById(productId);
    }

    @Override
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

    private void sendLowStockNotification(ProductEntity product) {
        logger.warn("Notification: Low stock for product: {}", product.getName());
        // Notification logic (email, SMS, etc.) would go here
    }
}
