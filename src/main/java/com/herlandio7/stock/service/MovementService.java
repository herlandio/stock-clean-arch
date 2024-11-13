package com.herlandio7.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.herlandio7.stock.model.Movement;
import com.herlandio7.stock.repository.MovementRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovementService {

    private final MovementRepository movementRepository;

    public List<Movement> listMovements() {
        return movementRepository.findAll();
    }

    public Movement addMovement(Movement movement) {
        return movementRepository.save(movement);
    }
}
