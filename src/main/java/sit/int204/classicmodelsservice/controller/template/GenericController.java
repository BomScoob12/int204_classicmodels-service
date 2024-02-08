package sit.int204.classicmodelsservice.controller.template;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.int204.classicmodelsservice.services.template.ServiceInterface;

import java.util.List;

public abstract class GenericController <S extends ServiceInterface<ENTITY, KEY_TYPE>, ENTITY, KEY_TYPE> {
    protected final S service;
    @Autowired
    public GenericController(S service) {
        this.service = service;
    }

    @GetMapping("")
    public List<ENTITY> getAllEntities() {
        return service.getAllEntities();
    }

    @GetMapping("/{id}")
    public ENTITY getEntityById(@PathVariable KEY_TYPE id) {
        return service.getEntity(id);
    }

    @PostMapping("")
    public ENTITY addNewEntity(@RequestBody ENTITY entity) {
        return service.createNewEntity(entity);
    }

    @PutMapping("/{id}")
    public ENTITY updateEntity(@PathVariable KEY_TYPE id, @RequestBody ENTITY entity) {
        return service.updateEntity(id, entity);
    }

    @DeleteMapping("/{id}")
    public void removeEntity(@PathVariable KEY_TYPE id) {
        service.removeEntity(id);
    }
}