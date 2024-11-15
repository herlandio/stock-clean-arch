package com.herlandio7.stock.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.herlandio7.stock.application.gateways.IProductGateway;
import com.herlandio7.stock.application.usecases.ProductInteractor;
import com.herlandio7.stock.infra.controllers.ProductDtoMapper;
import com.herlandio7.stock.infra.gateways.ProductEntityMapper;
import com.herlandio7.stock.infra.gateways.ProductRepositoryGateway;
import com.herlandio7.stock.infra.persistence.ProductRepository;

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
