package com.herlandio7.stock.infra.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.herlandio7.stock.application.usecases.MovementInteractor;
import com.herlandio7.stock.domain.entity.Movement;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/movements")
@RequiredArgsConstructor
public class MovementController {
    
    private final MovementInteractor movementInteractor;
    private final MovementDtoMapper movementDtoMapper;

    @PostMapping
    public ResponseEntity<CreateMovementResponse> addMovement(@RequestBody CreateMovementRequest movement) {
        Movement movementBusinessObj = movementDtoMapper.toMovement(movement);
        Movement newMovement = movementInteractor.addMovement(movementBusinessObj);
        return ResponseEntity.status(HttpStatus.CREATED).body(movementDtoMapper.toResponse(newMovement));
    }

    //TODO verificar
    @GetMapping
    public ResponseEntity<List<Movement>> listMovements() {
        List<Movement> movements = movementInteractor.listMovements();
        return ResponseEntity.ok(movements);
    }
}
