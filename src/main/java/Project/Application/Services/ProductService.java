package Project.Application.Services;


import Project.Application.DTO.ProductDTO;
import Project.Domain.Interfaces.ProductRepository;
import Project.Domain.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public ProductDTO getProductById(String id) {
        Optional<Product> productOpt = productRepository.findById(id);
        return productOpt.map(this::convertToDTO).orElse(null); // Convert to DTO
    }

    public List<ProductDTO> getProductsByName(String name) {
        List<Product> products = productRepository.findByName(name);
        return products.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);  // Convert DTO to Domain Entity
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct); // Convert back to DTO
    }

    private ProductDTO convertToDTO(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getPrice());
    }

    private Product convertToEntity(ProductDTO productDTO) {
        return new Product(productDTO.getId(), productDTO.getName(), productDTO.getPrice());
    }


}
