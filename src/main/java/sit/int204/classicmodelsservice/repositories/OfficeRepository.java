package sit.int204.classicmodelsservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int204.classicmodelsservice.entities.Office;

public interface OfficeRepository extends JpaRepository<Office, String> {
}
