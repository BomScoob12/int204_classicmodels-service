package sit.int204.classicmodelsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.int204.classicmodelsservice.entities.Product;
import sit.int204.classicmodelsservice.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController{
    @Autowired
    ProductService service;

    @GetMapping("")
    public List<Product> findAllProduct(@RequestParam(required = false, defaultValue = "0") Double lower,
                                        @RequestParam(required = false, defaultValue = "0") Double upper,
                                        @RequestParam(required = false, defaultValue = "") String name) {
        return service.findAllEntities(name, lower, upper);
    }

    @GetMapping("/product-line/{id}")
    public List<Product> findProductByProductLine(@PathVariable String id) {
        return service.findProductByProductLine(id);
    }
}
