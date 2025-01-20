package Project.Application.Services;


import Project.Application.DTO.ProductDTO;
import Project.Domain.Interfaces.ProductRepository;
import Project.Domain.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(String id) {
        Product product = productRepository.findById(id);
        return product != null ? convertToDTO(product) : null;
    }

    public List<ProductDTO> getProductsByName(String name) {
        List<Product> products = productRepository.findByName(name);
        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }

    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
        Product product = productRepository.findById(id);
        if (product != null) {
            product.setName(productDTO.getName());
            product.setPrice(productDTO.getPrice());
            productRepository.save(product);
            return convertToDTO(product);
        }
        return null;
    }

    public boolean deleteProduct(String id) {
        productRepository.deleteById(id);
        return false;
    }

    private ProductDTO convertToDTO(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getPrice());
    }

    private Product convertToEntity(ProductDTO productDTO) {
        return new Product(productDTO.getId(), productDTO.getName(), productDTO.getPrice());
    }
}