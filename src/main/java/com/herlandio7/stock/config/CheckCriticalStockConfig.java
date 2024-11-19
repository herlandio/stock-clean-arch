package com.herlandio7.stock.config;

import com.herlandio7.stock.application.gateways.ICheckCriticalStock;
import com.herlandio7.stock.application.gateways.IProductGateway;
import com.herlandio7.stock.application.usecases.CheckCriticalStockInteractor;
import com.herlandio7.stock.infra.config.messaging.KafkaTopicsConfig;
import com.herlandio7.stock.infra.gateways.CheckCriticalStockGateway;

import io.micrometer.core.instrument.MeterRegistry;

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
    public ICheckCriticalStock checkCriticalStockGateway(
        IProductGateway iProductGateway,
        KafkaTemplate<String, String> kafkaProducer,
        MeterRegistry meterRegistry,
        KafkaTopicsConfig kafkaTopicsConfig
    ) {
        return new CheckCriticalStockGateway(
            iProductGateway,
            kafkaProducer,
            meterRegistry,
            kafkaTopicsConfig
        );
    }
}
