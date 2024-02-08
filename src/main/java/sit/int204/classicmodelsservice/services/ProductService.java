package sit.int204.classicmodelsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sit.int204.classicmodelsservice.entities.Product;
import sit.int204.classicmodelsservice.repositories.ProductRepository;
import sit.int204.classicmodelsservice.services.template.ServiceInterface;

import java.util.List;

@Service
public class ProductService implements ServiceInterface<Product, String> {
    @Autowired
    ProductRepository repository;

    public List<Product> findAllEntities() {
        return repository.findAll();
    }

    public List<Product> findAllEntities(String name, Double lower, Double upper) {
        if (lower > upper) {
            double temp = upper;
            upper = lower;
            lower = temp;
        }
        if (upper + lower > 0) {
            return repository.findByProductNameContainingIgnoreCaseAndPriceBetween(name, lower, upper);
        } else {
            return repository.findByProductNameContainingIgnoreCase(name);
        }
    }

    public List<Product> findProductByProductLine(String productLine) {
        return repository.findByProductLineStartingWith(productLine);
    }

    @Override
    public List<Product> getAllEntities() {
        return repository.findAll();
    }

    @Override
    public Product getEntity(String id) {
        return null;
    }

    @Override
    public Product createNewEntity(Product product) {
        return null;
    }

    @Override
    public Product updateEntity(String id, Product product) {
        return null;
    }

    @Override
    public void removeEntity(String id) {

    }
}
