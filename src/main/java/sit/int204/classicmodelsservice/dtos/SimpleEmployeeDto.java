package sit.int204.classicmodelsservice.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleEmployeeDto {
    private Integer id;
    @JsonIgnore
    private String firstName;
    @JsonIgnore
    private String lastName;
    public String getName() {
        return firstName + ' '+ lastName;
    }
}
