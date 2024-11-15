package com.herlandio7.stock.infra.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.herlandio7.stock.domain.entity.Movement;

public class MovementDtoMapper {
    
    CreateMovementResponse toResponse(Movement movement){
        return new CreateMovementResponse(
            movement.product(),
            movement.quantity(),
            movement.type(),
            movement.movementDate()
        );
    }

    public Movement toMovement(CreateMovementRequest movement) {
        return new Movement(
            movement.product(), 
            movement.quantity(), 
            movement.type(), 
            movement.movementDate()
        );
    }

    List<CreateMovementResponse> toResponseList(List<Movement> movementList) {
        return movementList.stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
    }
}
