package com.herlandio7.stock.application.usecases;

import java.util.List;
import java.util.Optional;

import com.herlandio7.stock.application.gateways.IProductGateway;
import com.herlandio7.stock.domain.entity.Product;

public class ProductInteractor {
    
    private IProductGateway productGateway;

    public ProductInteractor(IProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public Product addProduct(Product product) {
        return productGateway.addProduct(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        return productGateway.updateProduct(id, updatedProduct);
    }

    public List<Product> listProducts() {
        return productGateway.listProducts();
    }

    public Optional<Product> getProductById(Long id){
        return productGateway.getProductById(id);
    }

    public void deleteProduct(Long id){
        productGateway.deleteProduct(id);
    }

}
