package com.herlandio7.stock.infra.controllers.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.herlandio7.stock.domain.entity.Movement;
import com.herlandio7.stock.infra.controllers.dtos.MovementRequest;
import com.herlandio7.stock.infra.controllers.dtos.MovementResponse;

public class MovementDtoMapper {
    
    public MovementResponse toResponse(Movement movement){
        return new MovementResponse(
            movement.product(),
            movement.quantity(),
            movement.type(),
            movement.movementDate()
        );
    }

    public Movement toMovement(MovementRequest movement) {
        return new Movement(
            movement.product(), 
            movement.quantity(), 
            movement.type(), 
            movement.movementDate()
        );
    }

    public List<MovementResponse> toResponseList(List<Movement> movementList) {
        return movementList.stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
    }
}
