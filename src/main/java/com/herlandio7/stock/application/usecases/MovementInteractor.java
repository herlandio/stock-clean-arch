package com.herlandio7.stock.application.usecases;

import java.util.List;

import com.herlandio7.stock.application.gateways.IMovementGateway;
import com.herlandio7.stock.domain.entity.Movement;

public class MovementInteractor {

    private IMovementGateway movementGateway;

    public MovementInteractor(IMovementGateway movementGateway) {
        this.movementGateway = movementGateway;
    }

    public List<Movement> listMovements() {
        return movementGateway.listMovements();
    }
    
    public Movement addMovement(Movement movement) {
        return movementGateway.addMovement(movement);
    }
}
