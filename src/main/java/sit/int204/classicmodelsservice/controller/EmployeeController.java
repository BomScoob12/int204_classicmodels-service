package sit.int204.classicmodelsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.int204.classicmodelsservice.entities.Employee;
import sit.int204.classicmodelsservice.services.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @GetMapping("")
    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping("/{empNumber}")
    public Employee getEmployeeById(@PathVariable Integer empNumber) {
        return service.getEmployee(empNumber);
    }

    @PostMapping("")
    public Employee addNewEmployee(@RequestBody Employee employee) {
        return service.createNewEmployee(employee);
    }

    @PutMapping("/{empNumber}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable Integer empNumber) {
        return service.updateEmployee(empNumber, employee);
    }

    @DeleteMapping("/{empNumber}")
    public void removeEmployee(@PathVariable Integer empNumber){
        service.removeEmployee(empNumber);
    }
}
