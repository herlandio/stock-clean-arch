package com.herlandio7.stock.infra.gateways;

import java.util.List;

import com.herlandio7.stock.application.gateways.IMovementGateway;
import com.herlandio7.stock.domain.entity.Movement;
import com.herlandio7.stock.infra.gateways.mappers.MovementEntityMapper;
import com.herlandio7.stock.infra.persistence.entities.MovementEntity;
import com.herlandio7.stock.infra.persistence.repositories.MovementRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MovementRepositoryGateway implements IMovementGateway {

    private final MovementRepository movementRepository;
    private final MovementEntityMapper movementEntityMapper;

    public List<Movement> listMovements() {
        List<MovementEntity> listMovements = movementRepository.findAll();
        return movementEntityMapper.toDomainList(listMovements);
    }

    public Movement addMovement(Movement movement) {
        MovementEntity movimentSave = movementEntityMapper.toEntity(movement);
        MovementEntity movementEntity = movementRepository.save(movimentSave);
        return movementEntityMapper.toDomain(movementEntity);
    }
}
