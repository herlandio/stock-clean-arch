package com.herlandio7.stock.infra.gateways;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.herlandio7.stock.application.gateways.ICheckCriticalStock;
import com.herlandio7.stock.application.gateways.IProductGateway;
import com.herlandio7.stock.domain.entity.Product;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CheckCriticalStockGateway implements ICheckCriticalStock {
    
    private static final Logger logger = LoggerFactory.getLogger(CheckCriticalStockGateway.class);
    private final IProductGateway productGateway;

    @Override
    public void execute() {
        List<Product> products = productGateway.listProducts();
        products.stream()
                .filter(product -> product.stockQuantity() <= product.criticalLevel())
                .forEach(this::sendLowStockNotification);
    }

    private void sendLowStockNotification(Product product) {
        logger.warn("Notification: Low stock for product: {}", product.name());
    }
}
