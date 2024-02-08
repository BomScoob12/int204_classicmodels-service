package sit.int204.classicmodelsservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int204.classicmodelsservice.entities.CustomerA;

import java.util.List;

public interface CustomerARepository extends JpaRepository<CustomerA, Long> {
    List<CustomerA> findByFirstNameContains(String name);
}
