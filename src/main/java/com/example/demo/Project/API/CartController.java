package com.example.demo.Project.API;

import com.example.demo.Project.Domain.Models.Cart;
import com.example.demo.Project.Domain.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Project.Application.Services.CartService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<Cart> createOrUpdateCart(@RequestBody Cart cart) {
        Cart savedCart = cartService.saveCart(cart);
        return new ResponseEntity<>(savedCart, HttpStatus.CREATED);
    }

    // Get a cart by ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Cart>> getCartById(@PathVariable Long id) {
        Optional<Optional<Cart>> cart = cartService.getCartById(id);
        return cart.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Get all carts
    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cartService.getAllCarts();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    // Delete a cart
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }

    // Ajouter un produit au panier
    @PostMapping("/{cartId}/add/{productId}")
    public ResponseEntity<Cart> addToCart(@PathVariable Long cartId, @PathVariable Long productId, @RequestParam int quantity) {
        Cart updatedCart = cartService.addToCart(cartId, productId, quantity);
        return ResponseEntity.ok(updatedCart);
    }

    // Retirer un produit du panier
    @DeleteMapping("/{cartId}/remove/{productId}")
    public ResponseEntity<Cart> removeFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        Cart updatedCart = cartService.removeFromCart(cartId, productId);
        return ResponseEntity.ok(updatedCart);
    }


}
