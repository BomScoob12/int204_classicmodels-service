package sit.int204.classicmodelsservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import sit.int204.classicmodelsservice.repositories.ProductRepository;

@Entity
@Table(name="products")
@Data
public class Product {
    @Id
    private String productCode;
    private String productName;
    private String productLine;
//    @ManyToOne
//    @JoinColumn(name = "productLine")
//    private ProductLine productLine;
    private String productScale;
    private String productVendor;
    private String productDescription;
    private Integer quantityInStock;
    @JsonIgnore
    private Double buyPrice;
    @Column(name = "msrp")
    private Double price;
}
