package com.herlandio7.stock.infra.gateways;

import java.util.List;
import java.util.stream.Collectors;

import com.herlandio7.stock.domain.entity.Movement;
import com.herlandio7.stock.domain.entity.Product;
import com.herlandio7.stock.infra.persistence.MovementEntity;
import com.herlandio7.stock.infra.persistence.ProductEntity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MovementEntityMapper {
    
    private final ProductEntityMapper productEntityMapper;

    public Movement toDomain(MovementEntity movementEntity) {
        Product product = productEntityMapper.toDomain(movementEntity.getProduct());
        return new Movement(
            product, 
            movementEntity.getQuantity(),
            movementEntity.getType(),
            movementEntity.getMovementDate()
        );
    }

    public MovementEntity toEntity(Movement movementInteractor) {
        ProductEntity productEntity = productEntityMapper.toEntity(movementInteractor.product());
        return new MovementEntity(
            productEntity, 
            movementInteractor.quantity(),
            movementInteractor.type(),
            movementInteractor.movementDate()
        );
    }

    List<Movement> toDomainList(List<MovementEntity> movementEntities) {
        return movementEntities.stream()
            .map(this::toDomain)
            .collect(Collectors.toList());
    }
}
