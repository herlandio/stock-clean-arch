package com.herlandio7.stock.infra.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.herlandio7.stock.application.usecases.MovementInteractor;
import com.herlandio7.stock.domain.entity.Movement;
import com.herlandio7.stock.infra.controllers.dtos.MovementRequest;
import com.herlandio7.stock.infra.controllers.dtos.MovementResponse;
import com.herlandio7.stock.infra.controllers.mappers.MovementDtoMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/movements")
@RequiredArgsConstructor
@Tag(name = "Movements", description = "API for managing stock movements")
@Validated
@Slf4j
public class MovementController {

    private final MovementInteractor movementInteractor;
    private final MovementDtoMapper movementDtoMapper;

    /**
     * Adds a new movement.
     *
     * @param movementRequest the movement request payload
     * @return the created movement as a response
     */
    @PostMapping
    @Operation(summary = "Create a new movement", description = "Adds a new movement to the stock.")
    public ResponseEntity<MovementResponse> addMovement(@Valid @RequestBody MovementRequest movementRequest) {
        try {
            log.info("Adding a new movement: {}", movementRequest);
            Movement movementBusinessObj = movementDtoMapper.toMovement(movementRequest);
            Movement newMovement = movementInteractor.addMovement(movementBusinessObj);
            MovementResponse response = movementDtoMapper.toResponse(newMovement);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            log.error("Error adding movement: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            // Para qualquer outra exceção inesperada
            log.error("Unexpected error occurred while adding movement: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Lists all movements.
     *
     * @return a list of movements
     */
    @GetMapping
    @Operation(summary = "List movements", description = "Retrieves a list of all stock movements.")
    public ResponseEntity<List<MovementResponse>> listMovements() {
        try {
            log.info("Fetching all movements.");
            List<Movement> movements = movementInteractor.listMovements();
            List<MovementResponse> responses = movementDtoMapper.toResponseList(movements);
            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            log.error("Error fetching movements: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}