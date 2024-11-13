package com.herlandio7.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.herlandio7.stock.model.Movement;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {}
