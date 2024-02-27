package sit.int204.classicmodelsservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleCustomerDTO {
    private String customerName;
    private String phone;
    private String city;
    private String country;
    private SimpleEmployeeDTO employeeRep;

    public String getEmployeeRep() {
        return employeeRep == null ?  "-" : employeeRep.getName() + "-" + employeeRep.getOfficeCity();
    }
}
