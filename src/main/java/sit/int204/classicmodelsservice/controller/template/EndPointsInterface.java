package sit.int204.classicmodelsservice.controller.template;

import org.springframework.web.bind.annotation.*;

import java.util.List;

interface EndPointsInterface<E, KEY> {
    @GetMapping("")
    List<E> getAllEntities();

    @GetMapping("/{id}")
    E getEntityById(@PathVariable KEY id);

    @PostMapping("")
    E addNewEntity(@RequestBody E entity);

    @PutMapping("/{id}")
    E updateEntity(@PathVariable KEY id, @RequestBody E entity);

    @DeleteMapping("/{id}")
    void removeEntity(@PathVariable KEY id);
}
