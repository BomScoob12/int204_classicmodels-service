package sit.int204.classicmodelsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import sit.int204.classicmodelsservice.entities.Order;
import sit.int204.classicmodelsservice.repositories.OrderRepository;
import sit.int204.classicmodelsservice.services.template.ServiceInterface;

import java.util.List;

@Service
public class OrderService implements ServiceInterface<Order, Integer> {
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

    @Override
    public Order createNewEntity(Order order) {
        return repository.save(order);
    }

    @Override
    public Order updateEntity(Integer id, Order order) {
        if (order.getId() != null && order.getId() != 0) {
            if (!order.getId().equals(id)) {
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Conflict Order number ( " + id + " vs " + order.getId());
            }
        }
        //check customer id has in database
        if (repository.existsById(id)){
            return repository.save(order);
        } else {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Order ID: " + id + "is does not exists");
        }

    }

    @Override
    public void removeEntity(Integer id) {

    }
}
