package sit.int204.classicmodelsservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int204.classicmodelsservice.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
