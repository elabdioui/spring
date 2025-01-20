package com.example.demo.Project.Domain.Interfaces;


import com.example.demo.Project.Domain.Models.Cart;
import com.example.demo.Project.Domain.Models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface CartRepository extends MongoRepository<Cart, Long> {
    Optional<Cart> findById(Long id);

}
