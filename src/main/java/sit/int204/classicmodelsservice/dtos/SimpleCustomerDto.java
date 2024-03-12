package sit.int204.classicmodelsservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleCustomerDto {
    private String customerName;
    private String phone;
    private String city;
    private String country;
    private SimpleEmployeeDto employeeRep;

    public String getEmployeeRep() {
        return employeeRep == null ?  "-" : employeeRep.getName();
    }
}
