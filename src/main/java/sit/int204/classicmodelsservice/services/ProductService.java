package sit.int204.classicmodelsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import sit.int204.classicmodelsservice.entities.Product;
import sit.int204.classicmodelsservice.repositories.ProductRepository;
import sit.int204.classicmodelsservice.services.template.ServiceInterface;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements ServiceInterface<Product, String> {
    @Autowired
    ProductRepository repository;

    public List<Product> findAllEntities(String name, Double lower, Double upper, String[] sortBy, String[] direction) {

        List<Sort.Order> sortProducts = new ArrayList<>();

        if (sortBy != null && sortBy.length != 0) {
            if (sortBy.length != direction.length)
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Sort value and direction value should have the same length.");
            for (int i = 0; i < sortBy.length; i++) {
                Sort.Order sortOrder = new Sort.Order(Sort.Direction.fromString(direction[i]), sortBy[i]);
                sortProducts.add(sortOrder);
            }

            if (lower > upper) {
                double temp = upper;
                upper = lower;
                lower = temp;
            }

            if (upper <= 0 && lower <= 0) {
                return repository.findByProductNameContainingIgnoreCase(name, Sort.by(sortProducts));
            } else {
                return repository.findByProductNameContainingIgnoreCaseAndPriceBetween(name, lower, upper, Sort.by(sortProducts));
            }
        } else {
            if (lower > upper) {
                double temp = upper;
                upper = lower;
                lower = temp;
            }

            if (upper <= 0 && lower <= 0) {
                return repository.findByProductNameContainingIgnoreCase(name);
            } else {
                return repository.findByProductNameContainingIgnoreCaseAndPriceBetween(name, lower, upper);
            }
        }
    }

    public Page<Product> findAllEntitiesByPage(String name, Double lower, Double upper, String[] sortBy, String[] direction, int pageNo, int pageSize) {
        List<Sort.Order> sortProducts = new ArrayList<>();

        if (lower > upper) {
            double temp = upper;
            upper = lower;
            lower = temp;
        }
        // page size = o, will be give all data to Page
        if (pageSize <= 0) pageSize = (int) repository.count();

        if (sortBy != null && sortBy.length != 0) {
            for (int i = 0; i < sortBy.length; i++) {
                Sort.Order sortOrder = new Sort.Order(Sort.Direction.fromString(direction[i]), sortBy[i]);
                sortProducts.add(sortOrder);
            }

            Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortProducts));

            if (upper <= 0 && lower <= 0) {
                return repository.findByProductNameContainingIgnoreCase(name, pageable);
            } else {
                return repository.findByProductNameContainingIgnoreCaseAndPriceBetween(name, lower, upper, pageable);
            }
        } else {
            Pageable pageable = PageRequest.of(pageNo, pageSize);
            if (upper <= 0 && lower <= 0) {
                return repository.findByProductNameContainingIgnoreCase(name, pageable);
            } else {
                return repository.findByProductNameContainingIgnoreCaseAndPriceBetween(name, lower, upper, pageable);
            }
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
