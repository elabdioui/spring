package com.example.demo.Project.Domain.Models;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Document(collection = "cart")
public class Cart {
    private Long id;
    private Map<Long, CartProduct> products; // Utilisation d'une Map pour associer un produit à sa quantité

    public Cart() {
        this.products = new HashMap<>();
    }

    public Cart(Long id, Map<Long, CartProduct> products) {
        this.id = id;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<Long, CartProduct> getProducts() {
        return products;
    }

    public void setProducts(Map<Long, CartProduct> products) {
        this.products = products;
    }

    public void addProduct(Product product, int quantity) {
        this.products.put(product.getId(), new CartProduct(product, quantity));
    }

    public void removeProduct(Long productId) {
        this.products.remove(productId);
    }

    public void updateProductQuantity(Long productId, int quantity) {
        CartProduct cartProduct = this.products.get(productId);
        if (cartProduct != null) {
            cartProduct.setQuantity(cartProduct.getQuantity() + quantity);
        }
    }
}
