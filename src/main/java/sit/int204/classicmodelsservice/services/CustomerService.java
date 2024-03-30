package sit.int204.classicmodelsservice.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;
import sit.int204.classicmodelsservice.dtos.NewCustomerDto;
import sit.int204.classicmodelsservice.entities.Customer;
import sit.int204.classicmodelsservice.entities.Order;
import sit.int204.classicmodelsservice.exceptions.ItemNotFoundException;
import sit.int204.classicmodelsservice.repositories.CustomerRepository;

import java.util.List;
import java.util.Set;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository repository;
    @Autowired
    ModelMapper mapper;
    @Autowired
    ListMapper listMapper;
    @Autowired
    EmployeeService service;

    public List<Customer> getCustomers() {
        return repository.findAll();
    }

    public Page<Customer> getCustomers(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    public Customer getCustomer(Integer customerId) {
        return repository.findById(customerId).orElseThrow(() -> new ItemNotFoundException("Customer not found Id " + customerId + " Does not exist."));
    }

    public Set<Order> getCustomerOrder(Integer customerId) {
        return this.getCustomer(customerId).getOrders();
    }

    @Transactional
    public Customer createNewCustomer(Customer customer, Integer empNumber) {
        if (customer == null || empNumber == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Customer or Employee is doesn't exist or invalid data !!!");
        } else {
            customer.setSales(service.getEmployee(empNumber));
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

    public NewCustomerDto createCustomer(NewCustomerDto newCustomer) {
        if(repository.existsById(newCustomer.getId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicate customer for id "+
                    newCustomer.getId());
        }
        Customer customer = mapper.map(newCustomer, Customer.class);
        return mapper.map(repository.saveAndFlush(customer), NewCustomerDto.class);
    }
    public List<NewCustomerDto> getAllCustomers() {
        return listMapper.mapList(repository.findAll(), NewCustomerDto.class, mapper);
    }
}
