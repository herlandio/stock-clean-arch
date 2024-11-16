package com.herlandio7.stock.infra.gateways;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import com.herlandio7.stock.application.gateways.ICheckCriticalStock;
import com.herlandio7.stock.application.gateways.IProductGateway;
import com.herlandio7.stock.domain.entity.Product;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CheckCriticalStockGateway implements ICheckCriticalStock {
    
    private static final Logger logger = LoggerFactory.getLogger(CheckCriticalStockGateway.class);
    private final IProductGateway productGateway;
    private static final String TOPIC = "low-stock-notifications";
    private final KafkaTemplate<String, String> kafkaTemplate;
    private Set<Long> processedProductIds = new HashSet<>();

    @Override
    public void execute() {
        List<Product> products = productGateway.listProducts();
        products.stream()
            .filter(product -> product.stockQuantity() <= product.criticalLevel())
            .filter(product -> !processedProductIds.contains(product.id()))
            .forEach(product -> {
                sendLowStockNotification(product);
                processedProductIds.add(product.id());
            });
    }

    private void sendLowStockNotification(Product product) {
        String message = String.format("Low stock for product: %s (ID: %s)", product.name(), product.id());
        logger.warn("Notification: {}", message);

        kafkaTemplate.send(TOPIC, String.valueOf(product.id()), message)
                .thenAccept(result -> logger.info("Message sent to topic {} with offset {}",
                        result.getRecordMetadata().topic(), result.getRecordMetadata().offset()))
                .exceptionally(ex -> {
                    logger.error("Failed to send message to Kafka", ex);
                    return null;
                });
    }
}
