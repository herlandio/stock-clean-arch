package com.herlandio7.stock.infra.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.herlandio7.stock.infra.persistence.entities.MovementEntity;

@Repository
public interface MovementRepository extends JpaRepository<MovementEntity, Long> {}
