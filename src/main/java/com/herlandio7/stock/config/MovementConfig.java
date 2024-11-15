package com.herlandio7.stock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.herlandio7.stock.application.gateways.IMovementGateway;
import com.herlandio7.stock.application.usecases.MovementInteractor;
import com.herlandio7.stock.infra.controllers.mappers.MovementDtoMapper;
import com.herlandio7.stock.infra.gateways.MovementRepositoryGateway;
import com.herlandio7.stock.infra.gateways.mappers.MovementEntityMapper;
import com.herlandio7.stock.infra.gateways.mappers.ProductEntityMapper;
import com.herlandio7.stock.infra.persistence.repositories.MovementRepository;

@Configuration
public class MovementConfig {
    
    @Bean
    MovementInteractor movementInteractorCase(IMovementGateway movementGateway) {
        return new MovementInteractor(movementGateway); 
    }

    @Bean
    IMovementGateway movementGateway(MovementRepository movementRepository, MovementEntityMapper movementEntityMapper){
        return new MovementRepositoryGateway(movementRepository, movementEntityMapper);
    }

    @Bean
    MovementEntityMapper movementEntityMapper(ProductEntityMapper productEntityMapper) {
        return new MovementEntityMapper(productEntityMapper);
    }

    @Bean
    MovementDtoMapper movementDtoMapper() {
        return new MovementDtoMapper();
    }
}
