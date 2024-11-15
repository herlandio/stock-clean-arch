package com.herlandio7.stock.application.gateways;

import java.util.List;

import com.herlandio7.stock.domain.entity.Movement;

public interface IMovementGateway {
    List<Movement> listMovements();
    Movement addMovement(Movement movement);
}
