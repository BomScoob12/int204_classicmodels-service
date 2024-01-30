package sit.int204.classicmodelsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sit.int204.classicmodelsservice.entities.Order;
import sit.int204.classicmodelsservice.repositories.OrderRepository;

import java.util.List;

@Service
public class OrderService implements ServiceInterface<Order, Integer>{
    @Autowired
    OrderRepository repository;

    @Override
    public List<Order> getAllEntities() {
        return repository.findAll();
    }

    @Override
    public Order getEntity(Integer id) {
        return repository.findById(id).orElseThrow();
    }
}
