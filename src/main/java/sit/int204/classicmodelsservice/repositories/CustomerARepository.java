package sit.int204.classicmodelsservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int204.classicmodelsservice.entities.CustomerA;

public interface CustomerARepository extends JpaRepository<CustomerA, Long> {

}
