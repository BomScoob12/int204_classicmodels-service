package sit.int204.classicmodelsservice.controller.template;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.int204.classicmodelsservice.services.template.ServiceInterface;

import java.util.List;

public abstract class GenericController <S extends ServiceInterface<E, KEY>, E, KEY> implements EndPointsInterface<E, KEY>{
    protected final S service;
    @Autowired
    public GenericController(S service) {
        this.service = service;
    }

    @GetMapping("")
    public List<E> getAllEntities() {
        return service.getAllEntities();
    }

    @GetMapping("/{id}")
    public E getEntityById(@PathVariable KEY id) {
        return service.getEntity(id);
    }

    @PostMapping("")
    public E addNewEntity(@RequestBody E entity) {
        return service.createNewEntity(entity);
    }

    @PutMapping("/{id}")
    public E updateEntity(@PathVariable KEY id, @RequestBody E entity) {
        return service.updateEntity(id, entity);
    }

    @DeleteMapping("/{id}")
    public void removeEntity(@PathVariable KEY id) {
        service.removeEntity(id);
    }
}