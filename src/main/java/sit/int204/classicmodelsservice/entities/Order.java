package sit.int204.classicmodelsservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "orderNumber", nullable = false)
    private Integer id;
    private Date orderDate;
    private Date requiredDate;
    private Date shippedDate;
    private String status;
    private String comments;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customerNumber")
    private Customer customer;
}
