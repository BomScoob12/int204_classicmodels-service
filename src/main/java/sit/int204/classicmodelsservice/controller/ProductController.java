package sit.int204.classicmodelsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sit.int204.classicmodelsservice.entities.Product;
import sit.int204.classicmodelsservice.model.ProductPage;
import sit.int204.classicmodelsservice.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService service;

    @GetMapping("")
    public List<Product> findAllProduct(@RequestParam(required = false, defaultValue = "0") Double lower,
                                        @RequestParam(required = false, defaultValue = "0") Double upper,
                                        @RequestParam(required = false, defaultValue = "") String name,
                                        @RequestParam(required = false, defaultValue = "") String[] sortBy,
                                        @RequestParam(required = false, defaultValue = "asc") String[] sortDirection) {
        return service.findAllEntities(name, lower, upper, sortBy, sortDirection);
    }

    @GetMapping("/paging")
    public Page<Product> findAllProduct(@RequestParam(required = false, defaultValue = "0") Double lower,
                                        @RequestParam(required = false, defaultValue = "0") Double upper,
                                        @RequestParam(required = false, defaultValue = "") String name,
                                        @RequestParam(required = false, defaultValue = "") String[] sortBy,
                                        @RequestParam(required = false, defaultValue = "asc") String[] sortDirection,
                                        @RequestParam(required = false, defaultValue = "0") int pageNo,
                                        @RequestParam(required = false, defaultValue = "0") int pageSize) {

        return service.findAllEntitiesByPage(name, lower, upper, sortBy, sortDirection, pageNo, pageSize);
    }

    @GetMapping("/paging-response-entity")
    public ResponseEntity<Object> findAllProductByResponseEntity(@RequestParam(required = false, defaultValue = "0") Double lower,
                                                                 @RequestParam(required = false, defaultValue = "0") Double upper,
                                                                 @RequestParam(required = false, defaultValue = "") String name,
                                                                 @RequestParam(required = false, defaultValue = "") String[] sortBy,
                                                                 @RequestParam(required = false, defaultValue = "asc") String[] sortDirection,
                                                                 @RequestParam(required = false, defaultValue = "0") int pageNo,
                                                                 @RequestParam(required = false, defaultValue = "0") int pageSize) {
        if (pageSize == 0) {
            return ResponseEntity.ok(service.getAllEntities());
        } else {
            Page<Product> page = service.findAllEntitiesByPage(name, lower, upper, sortBy, sortDirection, pageNo, pageSize);
            ProductPage productPage = new ProductPage();
            productPage.setProductList(page.getContent());
            productPage.setPageNumber(page.getNumber());
            productPage.setPageSize(page.getSize());
            productPage.setTotalElement(page.getTotalElements());
            productPage.setTotalPage(page.getTotalPages());
            return ResponseEntity.ok(productPage);
        }
    }


    @GetMapping("/product-line/{id}")
    public List<Product> findProductByProductLine(@PathVariable String id) {
        return service.findProductByProductLine(id);
    }
}
