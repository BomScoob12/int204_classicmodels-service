package sit.int204.classicmodelsservice.services;

import java.util.List;

public interface ServiceInterface <ENTITY, KEY_TYPE> {
    List<ENTITY> getAllEntities();
    ENTITY getEntity(KEY_TYPE id);
}
