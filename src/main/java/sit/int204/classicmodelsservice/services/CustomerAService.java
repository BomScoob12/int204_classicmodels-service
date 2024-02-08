package sit.int204.classicmodelsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sit.int204.classicmodelsservice.entities.CustomerA;
import sit.int204.classicmodelsservice.repositories.CustomerARepository;
import sit.int204.classicmodelsservice.services.template.ServiceInterface;

import java.util.List;

@Service
public class CustomerAService implements ServiceInterface<CustomerA, Long> {
    @Autowired
    CustomerARepository repository;

    public List<CustomerA> insertCustomerAs(List<CustomerA> customerAList) {
        return repository.saveAll(customerAList);
    }

    public List<CustomerA> findAllCustomera() {
        return findAllCustomera(null);
    }

    public List<CustomerA> findAllCustomera(String name) {
        if (name == null || name.isEmpty()) {
            return repository.findAll();
        } else {
            return repository.findByFirstNameContains(name);
        }
    }
    @Override
    public List<CustomerA> getAllEntities() {
        return null;
    }

    @Override
    public CustomerA getEntity(Long id) {
        return null;
    }

    @Override
    public CustomerA createNewEntity(CustomerA customerA) {
        return null;
    }

    @Override
    public CustomerA updateEntity(Long id, CustomerA customerA) {
        return null;
    }

    @Override
    public void removeEntity(Long id) {

    }
}
