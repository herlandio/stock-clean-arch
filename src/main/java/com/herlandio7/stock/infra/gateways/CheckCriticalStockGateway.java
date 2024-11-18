package com.herlandio7.stock.infra.gateways;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.kafka.core.KafkaTemplate;

import com.herlandio7.stock.application.gateways.ICheckCriticalStock;
import com.herlandio7.stock.application.gateways.IProductGateway;
import com.herlandio7.stock.domain.entity.Product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CheckCriticalStockGateway implements ICheckCriticalStock {
    
    private final IProductGateway productGateway;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private Set<Long> processedProductIds = new HashSet<>();

    private static final String TOPIC_CRITICAL = "critical-stock-notifications";
    private static final String TOPIC_MODERATE = "moderate-stock-notifications";
    private static final String TOPIC_LOW = "low-stock-notifications";
    private static final String TOPIC_DLQ_SUFFIX = ".dlq";

    @Override
    public void execute() {
        List<Product> products = productGateway.listProducts();
        products.stream()
            .filter(product -> product.stockQuantity() <= product.criticalLevel())
            .filter(product -> !processedProductIds.contains(product.id()))
            .forEach(product -> {
                sendNotificationBasedOnStock(product);
                processedProductIds.add(product.id());
            });
    }

    private void sendNotificationBasedOnStock(Product product) {
        String topic = getTopicBasedOnStock(product);
        String message = String.format("Stock alert for product: %s (ID: %s), Current stock: %d, Critical level: %d",
                product.name(), product.id(), product.stockQuantity(), product.criticalLevel());

        log.info("Notification: {}", message);
        
        kafkaTemplate.send(topic, String.valueOf(product.id()), message)
            .thenAccept(result -> log.info("Message sent to topic {} with offset {}",
                    result.getRecordMetadata().topic(), result.getRecordMetadata().offset()))
            .exceptionally(ex -> {
                log.error("Failed to send message to Kafka, sending to DLQ", ex);
                sendToDeadLetterQueue(topic, String.valueOf(product.id()), message);
                return null;
            });
    }

    private String getTopicBasedOnStock(Product product) {
        int stockDifference = product.criticalLevel() - product.stockQuantity();

        if (stockDifference >= 10) {
            return TOPIC_CRITICAL;
        } else if (stockDifference >= 5) {
            return TOPIC_MODERATE;
        } else {
            return TOPIC_LOW;
        }
    }

    private void sendToDeadLetterQueue(String topic, String key, String value) {
        String dlqTopic = topic + TOPIC_DLQ_SUFFIX;
        kafkaTemplate.send(dlqTopic, key, value)
            .thenAccept(result -> log.info("Message sent to DLQ topic {} with offset {}",
                    result.getRecordMetadata().topic(), result.getRecordMetadata().offset()))
            .exceptionally(ex -> {
                log.error("Failed to send message to DLQ as well", ex);
                return null;
            });
    }
}
