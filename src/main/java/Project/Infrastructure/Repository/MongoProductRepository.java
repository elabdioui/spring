package Project.Infrastructure.Repository;

import Project.Domain.Interfaces.ProductRepository;
import Project.Domain.Models.Product;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MongoProductRepository implements ProductRepository {

    private final MongoTemplate mongoTemplate;

    public MongoProductRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public Optional<Product> findById(String id) {
        Product product = mongoTemplate.findById(id, Product.class);
        return Optional.ofNullable(product);
    }

    @Override
    public List<Product> findByName(String name) {
        return mongoTemplate.find(Query.query(Criteria.where("name").is(name)), Product.class);
    }

    @Override
    public Product save(Product product) {
        mongoTemplate.save(product);
        return product;
    }
}
