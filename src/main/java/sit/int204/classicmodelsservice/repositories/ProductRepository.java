package sit.int204.classicmodelsservice.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sit.int204.classicmodelsservice.entities.Product;
import sit.int204.classicmodelsservice.entities.ProductLine;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByProductNameContainingIgnoreCaseAndPriceBetween(String productName, Double lower, Double upper);
    List<Product> findByProductNameContainingIgnoreCaseAndPriceBetween(String productName, Double lower, Double upper, Sort sort);
    List<Product> findByProductNameContainingIgnoreCase(String productName);
    List<Product> findByProductNameContainingIgnoreCase(String productName, Sort sort);

    @Query("select p from Product p where p.price >= :lowerPrice and p.price <= :upperPrice and p.productName like :name")
    List<Product> findByPriceAndName(Double lowerPrice, Double upperPrice, String name);

    List<Product> findByProductLineStartingWith(String productLine);

    List<Product> findFirstByOrderByPriceDesc();
}
