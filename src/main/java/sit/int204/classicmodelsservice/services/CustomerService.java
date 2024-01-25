package sit.int204.classicmodelsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import sit.int204.classicmodelsservice.entities.Customer;
import sit.int204.classicmodelsservice.entities.Employee;
import sit.int204.classicmodelsservice.repositories.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository repository;

    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    public Customer getCustomer(Integer customerId) {
        return repository.findById(customerId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Customer Number " + customerId + " DOES NOT EXIST !!!"));
    }

    @Transactional
    public Customer createNewCustomer(Customer customer, Employee employee) {
        if (customer == null || employee == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Customer or Employee is doesn't exist or invalid data !!!");
        } else {
            customer.setEmployeeRep(employee);
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
