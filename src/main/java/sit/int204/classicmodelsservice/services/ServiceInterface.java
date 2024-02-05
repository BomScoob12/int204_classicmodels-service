package sit.int204.classicmodelsservice.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ServiceInterface <ENTITY, KEY_TYPE> {
    List<ENTITY> getAllEntities();
    ENTITY getEntity(KEY_TYPE id);
    ENTITY createNewEntity(ENTITY entity);
    ENTITY updateEntity(KEY_TYPE id ,ENTITY entity);
    void removeEntity(KEY_TYPE id);
}
