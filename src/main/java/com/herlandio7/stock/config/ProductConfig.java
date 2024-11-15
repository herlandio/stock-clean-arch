package com.herlandio7.stock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.herlandio7.stock.application.gateways.IProductGateway;
import com.herlandio7.stock.application.usecases.ProductInteractor;
import com.herlandio7.stock.infra.controllers.mappers.ProductDtoMapper;
import com.herlandio7.stock.infra.gateways.ProductRepositoryGateway;
import com.herlandio7.stock.infra.gateways.mappers.ProductEntityMapper;
import com.herlandio7.stock.infra.persistence.repositories.ProductRepository;

@Configuration
public class ProductConfig {
    
    @Bean
    ProductInteractor productInteractorCase(IProductGateway productGateway) {
        return new ProductInteractor(productGateway); 
    }

    @Bean
    IProductGateway productGateway(ProductRepository productRepository, ProductEntityMapper productEntityMapper){
        return new ProductRepositoryGateway(productRepository, productEntityMapper);
    }

    @Bean
    ProductEntityMapper productEntityMapper() {
        return new ProductEntityMapper();
    }

    @Bean
    ProductDtoMapper productDtoMapper() {
        return new ProductDtoMapper();
    }
}
