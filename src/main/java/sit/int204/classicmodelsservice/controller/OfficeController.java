package sit.int204.classicmodelsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.int204.classicmodelsservice.entities.Employee;
import sit.int204.classicmodelsservice.entities.Office;
import sit.int204.classicmodelsservice.services.OfficeService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/offices")
public class OfficeController {
    @Autowired
    private OfficeService service;

    @GetMapping("")
    public List<Office> getAllOffices(){
        return service.getAllOffice();
    }

    @GetMapping("/{officeCode}")
    public Office getOfficeById(@PathVariable String officeCode){
        return service.getOffice(officeCode);
    }

    @GetMapping("/{officeCode}/employees")
    public Set<Employee> getEmployees(@PathVariable String officeCode) {
        return service.getOffice(officeCode).getEmployees();
    }

    @PostMapping("")
    public Office addNewOffice(@RequestBody Office office){
        return service.createNewOffice(office);
    }

    @PutMapping("/{officeCode}")
    public Office updateOffice(@RequestBody Office office, @PathVariable String officeCode){
        return service.updateOffice(officeCode, office);
    }

    @DeleteMapping("/{officeCode}")
    public void removeOffice(@PathVariable String officeCode){
        service.removeOffice(officeCode);
    }
}
