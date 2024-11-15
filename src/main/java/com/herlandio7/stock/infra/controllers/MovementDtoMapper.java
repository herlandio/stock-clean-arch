package com.herlandio7.stock.infra.controllers;

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
}
