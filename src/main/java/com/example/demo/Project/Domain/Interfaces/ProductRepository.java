package com.example.demo.Project.Domain.Interfaces;

import com.example.demo.Project.Domain.Models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ProductRepository extends MongoRepository<Product, Long> {
    Product findById(String id);
    List<Product> findAll();
    Product save(Product product);
    void deleteById(String id);
    List<Product> findByName(String name);
}