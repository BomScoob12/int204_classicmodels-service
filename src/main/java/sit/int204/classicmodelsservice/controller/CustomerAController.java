package sit.int204.classicmodelsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.int204.classicmodelsservice.entities.CustomerA;
import sit.int204.classicmodelsservice.services.CustomerAService;

import java.util.List;

@RestController
@RequestMapping("/customeras")
public class CustomerAController {
    @Autowired
    CustomerAService service;

    @PostMapping("/saveall")
    public List<CustomerA> saveAllCustomeras(@RequestBody List<CustomerA> customerAList) {
        return service.insertCustomerAs(customerAList);
    }

    /* the name in @RequestParam is name field for front-end sending to back-end
    // but default using parameter name on method.
    // example : this method will be receiving by filterText but param name filterString
    */
    @GetMapping("")
    public List<CustomerA> findAllCustomera(@RequestParam(required = false) String filterString) {
        return service.findAllCustomera(filterString);
    }
}
