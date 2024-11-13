package com.herlandio7.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.herlandio7.stock.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {}
