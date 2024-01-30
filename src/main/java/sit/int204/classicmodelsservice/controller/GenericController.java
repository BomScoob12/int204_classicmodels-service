package sit.int204.classicmodelsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.int204.classicmodelsservice.services.ServiceInterface;

import java.util.List;

public final class GenericController <SERVICE extends ServiceInterface<ENTITY, KEY_TYPE>, ENTITY, KEY_TYPE> {
    public SERVICE service;

    @Autowired
    public void setService(SERVICE service) {
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

//    @PostMapping("")
//    public ENTITY addNewEntity(@RequestBody ENTITY entity) {
//        return service.createNewEntity(entity);
//    }
//
//    @PutMapping("/{id}")
//    public ENTITY updateEntity(@RequestBody ENTITY entity, @PathVariable KEY_TYPE id) {
//        return service.updateEntity(id, entity);
//    }
//
//    @DeleteMapping("/{id}")
//    public void removeEntity(@PathVariable KEY_TYPE id) {
//        service.removeEntity(id);
//    }
}