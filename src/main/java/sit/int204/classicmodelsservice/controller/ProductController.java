package sit.int204.classicmodelsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.int204.classicmodelsservice.entities.Product;
import sit.int204.classicmodelsservice.services.ProductService;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController{
    @Autowired
    ProductService service;

    @GetMapping("")
    public List<Product> findAllProduct(@RequestParam(required = false, defaultValue = "0") Double lower,
                                        @RequestParam(required = false, defaultValue = "0") Double upper,
                                        @RequestParam(required = false, defaultValue = "") String name,
                                        @RequestParam(required = false, defaultValue = "") String [] sortBy,
                                        @RequestParam(required = false, defaultValue = "asc") String sortDirection) {
        System.out.println(Arrays.stream(sortBy).toList());
        return service.findAllEntities(name, lower, upper, sortBy, sortDirection);
    }


    @GetMapping("/product-line/{id}")
    public List<Product> findProductByProductLine(@PathVariable String id) {
        return service.findProductByProductLine(id);
    }
}
