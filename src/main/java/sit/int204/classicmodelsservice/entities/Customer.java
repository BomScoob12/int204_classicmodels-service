package sit.int204.classicmodelsservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @Column(name = "customerNumber", nullable = false)
    private Integer id;
    @Column(name = "customerName", nullable = false)
    private String customerName;
    @Column(name = "contactLastName", nullable = false)
    private String contactLastName;
    @Column(name = "contactFirstName", nullable = false)
    private String contactFirstName;
    @Column(name = "phone", nullable = false)
    private String phone;
    @Column(name = "addressLine1", nullable = false)
    private String addressLine1;
    @Column(name = "addressLine2", nullable = false)
    private String addressLine2;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "state", nullable = false)
    private String state;
    @Column(name = "postalCode", nullable = false)
    private String postalCode;
    @Column(name = "country", nullable = false)
    private String country;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "salesRepEmployeeNumber", nullable = false)
    private Employee employeeRep;
    @Column(name = "creditLimit", nullable = false)
    private Double creditLimit;
}
