package Project.Infrastructure.Repository;

import Project.Domain.Interfaces.ProductRepository;
import Project.Domain.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MongoProductRepository implements ProductRepository {

    private final MongoProductRepository repository;

    @Autowired
    public MongoProductRepository(MongoRepository<Product, String> repository) {
        this.repository = (MongoProductRepository) repository;
    }

    @Override
    public List<Product> findByName(String name) {
        return repository.findAll().stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public Product findById(String id) {
        return repository.findById(id);
    }

}
