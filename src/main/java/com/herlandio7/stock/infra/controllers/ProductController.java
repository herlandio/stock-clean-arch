package com.herlandio7.stock.infra.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.herlandio7.stock.application.usecases.CheckCriticalStockInteractor;
import com.herlandio7.stock.application.usecases.ProductInteractor;
import com.herlandio7.stock.domain.entity.Product;
import com.herlandio7.stock.infra.controllers.dtos.ProductRequest;
import com.herlandio7.stock.infra.controllers.dtos.ProductResponse;
import com.herlandio7.stock.infra.controllers.mappers.ProductDtoMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Validated
@Slf4j
@Tag(name = "Products", description = "API for managing products")
public class ProductController {

    private final ProductInteractor productInteractor;
    private final CheckCriticalStockInteractor checkCriticalStockInteractor;
    private final ProductDtoMapper productDtoMapper;

    @PostMapping
    @Operation(summary = "Create a new product", description = "Adds a new product to the inventory.")
    public ResponseEntity<ProductResponse> addProduct(@Valid @RequestBody ProductRequest productRequest) {
        try {
            log.info("Adding new product: {}", productRequest);
            Product product = productDtoMapper.toProduct(productRequest);
            Product newProduct = productInteractor.addProduct(product);
            checkCriticalStockInteractor.execute();
            ProductResponse response = productDtoMapper.toResponse(newProduct);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            log.error("Error adding product: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    @Operation(summary = "List all products", description = "Retrieves all products in the inventory.")
    public ResponseEntity<List<ProductResponse>> listProducts() {
        try {
            log.info("Fetching all products.");
            List<Product> products = productInteractor.listProducts();
            List<ProductResponse> responses = productDtoMapper.toResponseList(products);
            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            log.error("Error fetching products: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID", description = "Retrieves details of a specific product.")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        try {
            log.info("Fetching product with ID: {}", id);
            return productInteractor.getProductById(id)
                    .map(product -> {
                        log.info("Product with ID {} found.", id);
                        return ResponseEntity.ok(productDtoMapper.toResponse(product));
                    })
                    .orElseGet(() -> {
                        log.warn("Product with ID {} not found.", id);
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                    });
        } catch (Exception e) {
            log.error("Error fetching product with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a product", description = "Updates details of a specific product.")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequest updatedProduct) {
        log.info("Updating product with ID: {}", id);
        try {
            Product productToUpdate = productDtoMapper.toProduct(updatedProduct);
            Product updated = productInteractor.updateProduct(id, productToUpdate);
            checkCriticalStockInteractor.execute();
            ProductResponse response = productDtoMapper.toResponse(updated);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            log.error("Error updating product: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product", description = "Removes a specific product from the inventory.")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
         try {
            log.info("Deleting product with ID: {}", id);
            productInteractor.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error deleting product with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
