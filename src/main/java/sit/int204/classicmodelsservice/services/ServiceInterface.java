package sit.int204.classicmodelsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import sit.int204.classicmodelsservice.repositories.OrderRepository;

import java.util.List;

public interface ServiceInterface <ENTITY, KEY_TYPE> {
    public List<ENTITY> getAllEntities();
    public ENTITY getEntity(KEY_TYPE id);
}
