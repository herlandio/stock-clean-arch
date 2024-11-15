package com.herlandio7.stock.application.usecases;

import java.util.List;

import com.herlandio7.stock.application.gateways.IProductGateway;
import com.herlandio7.stock.domain.entity.Product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CheckCriticalStockInteractor {

    private static final Logger logger = LoggerFactory.getLogger(CheckCriticalStockInteractor.class);
    private final IProductGateway productGateway;

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
