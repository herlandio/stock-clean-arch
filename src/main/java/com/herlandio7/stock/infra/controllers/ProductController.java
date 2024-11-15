package com.herlandio7.stock.infra.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.herlandio7.stock.application.usecases.ProductInteractor;
import com.herlandio7.stock.domain.entity.Product;
import com.herlandio7.stock.infra.controllers.dtos.ProductRequest;
import com.herlandio7.stock.infra.controllers.dtos.ProductResponse;
import com.herlandio7.stock.infra.controllers.mappers.ProductDtoMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductInteractor productInteractor;
    private final ProductDtoMapper productDtoMapper;

    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest product) {
        Product productBusinessObj = productDtoMapper.toProduct(product);
        Product newProduct = productInteractor.addProduct(productBusinessObj);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDtoMapper.toResponse(newProduct));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> listProducts() {
        List<Product> products = productInteractor.listProducts();
        List<ProductResponse> responses = productDtoMapper.toResponseList(products);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        Optional<Product> productById = productInteractor.getProductById(id);
        Optional<ProductResponse> response = productDtoMapper.toResponseById(productById);
        return response
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound()
                .build()
            );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody ProductRequest updatedProduct) {
        try {
            Product productRequest = productDtoMapper.toProduct(updatedProduct);
            Product product = productInteractor.updateProduct(id, productRequest);
            ProductResponse response = productDtoMapper.toResponse(product);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productInteractor.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}

