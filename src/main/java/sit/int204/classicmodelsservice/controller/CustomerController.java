package sit.int204.classicmodelsservice.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sit.int204.classicmodelsservice.entities.Customer;
import sit.int204.classicmodelsservice.entities.Order;
import sit.int204.classicmodelsservice.dtos.SimpleCustomerDTO;
import sit.int204.classicmodelsservice.services.CustomerService;
import sit.int204.classicmodelsservice.services.ListMapper;

import java.util.Set;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService service;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    ListMapper listMapper;

    @GetMapping("")
    public ResponseEntity<Object> getAllCustomers(@RequestParam(defaultValue = "false") boolean pageable,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int pageSize) {
        if (pageable) {
            Page<Customer> customerPage = service.getCustomers(page, pageSize);
            return ResponseEntity.ok(listMapper.toPageDTO(customerPage, SimpleCustomerDTO.class));
        } else {
            return ResponseEntity.ok(listMapper.mapList(service.getCustomers(), SimpleCustomerDTO.class));
        }
    }

    @GetMapping("/{customerNumber}")
    public ResponseEntity<Object> getCustomerById(@PathVariable Integer customerNumber) {
        SimpleCustomerDTO simpleCustomer = modelMapper.map(service.getCustomer(customerNumber), SimpleCustomerDTO.class);
        return ResponseEntity.ok(simpleCustomer);
    }

    @GetMapping("/{customerNumber}/orders")
    public Set<Order> getCustomerOrders(@PathVariable Integer customerNumber) {
        return service.getCustomerOrder(customerNumber);
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
