package sit.int204.classicmodelsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.int204.classicmodelsservice.entities.Customer;
import sit.int204.classicmodelsservice.entities.Employee;
import sit.int204.classicmodelsservice.services.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService service;

    @GetMapping("")
    public List<Customer> getAllCustomers() {
        return service.getAllCustomers();
    }

    @GetMapping("/{customerNumber}")
    public Customer getCustomerById(@PathVariable Integer customerNumber) {
        return service.getCustomer(customerNumber);
    }

    @PostMapping("")
    public Customer addNewCustomer(@RequestBody Customer customer, @RequestBody Integer empNumber) {
        return service.createNewCustomer(customer, empNumber);
    }

    @PutMapping("/{customerNumber}")
    public Customer updateCustomer(@RequestBody Customer customer, @PathVariable Integer customerNumber) {
        return service.updateCustomer(customerNumber, customer);
    }

    @DeleteMapping("/{customerNumber}")
    public void removeCustomer(@PathVariable Integer customerNumber) {
        service.removeCustomer(customerNumber);
    }
}
