package sit.int204.classicmodelsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.int204.classicmodelsservice.entities.Order;
import sit.int204.classicmodelsservice.services.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController extends GenericController<OrderService, Order, Integer>{

}
