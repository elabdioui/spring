package com.example.demo.Project.Application.Services;

import com.example.demo.Project.Domain.Interfaces.CartRepository;
import com.example.demo.Project.Domain.Interfaces.ProductRepository;
import com.example.demo.Project.Domain.Models.Cart;
import com.example.demo.Project.Domain.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService  {
    private final CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;


    @Autowired
    public CartService(CartRepository cartRepository) {

        this.cartRepository = cartRepository;
    }

    // Create or update a cart
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    // Get a cart by ID
    public Optional<Optional<Cart>> getCartById(Long id) {
        return Optional.of(cartRepository.findById(id));
    }

    // Get all carts
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    // Delete a cart
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }
    // Ajouter un produit au panier
    public Cart addToCart(Long cartId, Long productId, int quantity) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Panier non trouvé"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Produit non trouvé"));

        // Si le produit est déjà dans le panier, on met à jour la quantité
        if (cart.getProducts().containsKey(productId)) {
            cart.updateProductQuantity(productId, quantity);
        } else {
            cart.addProduct(product, quantity);
        }

        return cartRepository.save(cart);
    }

    // Retirer un produit du panier
    public Cart removeFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Panier non trouvé"));
        cart.removeProduct(productId);
        return cartRepository.save(cart);
    }



}

