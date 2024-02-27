package sit.int204.classicmodelsservice.model;

import lombok.Data;
import sit.int204.classicmodelsservice.entities.Product;

import java.util.List;

@Data
public class ProductPage {
    private List<Product> productList;
    private int pageNumber;
    private int pageSize;
    private long totalElement;
    private int totalPage;
}
