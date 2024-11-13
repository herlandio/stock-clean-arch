package com.herlandio7.stock.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.herlandio7.stock.model.Movement;
import com.herlandio7.stock.service.MovementService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/movements")
@RequiredArgsConstructor
public class MovementController {

    private final MovementService movementService;

    @GetMapping
    public ResponseEntity<List<Movement>> listMovements() {
        List<Movement> movements = movementService.listMovements();
        return ResponseEntity.ok(movements);
    }

    @PostMapping
    public ResponseEntity<Movement> addMovement(@RequestBody Movement movement) {
        Movement newMovement = movementService.addMovement(movement);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMovement);
    }
}
