package Project.Application.Services;


import Project.Application.DTO.ProductDTO;
import Project.Domain.Interfaces.ProductRepository;
import Project.Domain.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public ProductService(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
    }


}
