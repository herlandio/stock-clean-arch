package com.herlandio7.stock.infra.controllers;

import java.util.List;

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

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductInteractor productInteractor;
    private final ProductDtoMapper productDtoMapper;

    @PostMapping
    public ResponseEntity<CreateProductResponse> addProduct(@RequestBody CreateProductRequest product) {
        Product productBusinessObj = productDtoMapper.toProduct(product);
        Product newProduct = productInteractor.addProduct(productBusinessObj);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDtoMapper.toResponse(newProduct));
    }

    //TODO revisar pra baixo
    @GetMapping
    public ResponseEntity<List<Product>> listProducts() {
        List<Product> products = productInteractor.listProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productInteractor.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        try {
            Product product = productInteractor.updateProduct(id, updatedProduct);
            return ResponseEntity.ok(product);
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

