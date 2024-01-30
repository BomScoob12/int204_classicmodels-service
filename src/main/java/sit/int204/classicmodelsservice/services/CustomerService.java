package sit.int204.classicmodelsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import sit.int204.classicmodelsservice.entities.Customer;
import sit.int204.classicmodelsservice.entities.Order;
import sit.int204.classicmodelsservice.repositories.CustomerRepository;

import java.util.List;
import java.util.Set;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository repository;
    EmployeeService service;

    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    public Customer getCustomer(Integer customerId) {
        return repository.findById(customerId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Customer Number " + customerId + " DOES NOT EXIST !!!"));
    }

    public Set<Order> getCustomerOrder(Integer customerId) {
        return this.getCustomer(customerId).getOrders();
    }

    @Transactional
    public Customer createNewCustomer(Customer customer, Integer empNumber) {
        if (customer == null || empNumber == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Customer or Employee is doesn't exist or invalid data !!!");
        } else {
            customer.setEmployeeRep(service.getEmployee(empNumber));
            return repository.save(customer);
        }
    }

    @Transactional
    public void removeCustomer(Integer customerId) {
        Customer customer = this.getCustomer(customerId);
        repository.delete(customer);
    }

    public Customer updateCustomer(Integer customerId, Customer customer) {
        if (customer.getId() != null && customer.getId() != 0) {
            if (!customer.getId().equals(customerId)) {
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Conflict Employee number ( " + customerId + " vs " + customer.getId());
            }
        }
        //check customer id has in database
        this.getCustomer(customerId);
        return repository.save(customer);
    }
}
