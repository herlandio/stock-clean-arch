package com.herlandio7.stock.config;

import com.herlandio7.stock.application.gateways.ICheckCriticalStock;
import com.herlandio7.stock.application.gateways.IProductGateway;
import com.herlandio7.stock.application.usecases.CheckCriticalStockInteractor;
import com.herlandio7.stock.infra.gateways.CheckCriticalStockGateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class CheckCriticalStockConfig {

    @Bean
    public CheckCriticalStockInteractor checkCriticalStockUseCase(ICheckCriticalStock iCheckCriticalStock) {
        return new CheckCriticalStockInteractor(iCheckCriticalStock);
    }

    @Bean
    public ICheckCriticalStock checkCriticalStockGateway(IProductGateway iProductGateway, KafkaTemplate<String, String> kafkaProducer) {
        return new CheckCriticalStockGateway(iProductGateway, kafkaProducer);
    }
}
