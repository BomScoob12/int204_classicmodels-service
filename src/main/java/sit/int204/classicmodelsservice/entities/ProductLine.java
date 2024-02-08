package sit.int204.classicmodelsservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "productlines")
@Getter
@Setter
public class ProductLine {
    @Id
    @Column(name = "productLine", nullable = false)
    private String id;
    @Column(name = "textDescription")
    private String textDesc;
    @Column(name = "htmlDescription")
    private String htmlDesc;
    @OneToMany(mappedBy = "productLine")
    private List<Product> products;
}
