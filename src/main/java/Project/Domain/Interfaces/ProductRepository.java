package Project.Domain.Interfaces;

import Project.Domain.Models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findById(String id);
    List<Product> findByName(String name);  // Rechercher des produits par nom
    Product save(Product product);  // Sauvegarder un produit
}
