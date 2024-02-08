package sit.int204.classicmodelsservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.int204.classicmodelsservice.controller.template.GenericController;
import sit.int204.classicmodelsservice.entities.Order;
import sit.int204.classicmodelsservice.services.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController extends GenericController<OrderService, Order, Integer> {
    public OrderController(OrderService service) {
        super(service);
    }
}
