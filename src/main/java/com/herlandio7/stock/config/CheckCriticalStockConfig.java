package com.herlandio7.stock.config;

import com.herlandio7.stock.application.gateways.IProductGateway;
import com.herlandio7.stock.application.usecases.CheckCriticalStockInteractor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CheckCriticalStockConfig {

    @Bean
    public CheckCriticalStockInteractor checkCriticalStockUseCase(IProductGateway productGateway) {
        return new CheckCriticalStockInteractor(productGateway);
    }
}
