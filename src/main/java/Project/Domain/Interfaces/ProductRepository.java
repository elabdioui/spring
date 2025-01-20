package Project.Domain.Interfaces;

import Project.Domain.Models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> findByName(String name);
    List<Product> findAll();
    Product save(Product product);
    void deleteById(String id);
    Product findById(String id);
}
