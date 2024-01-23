package sit.int204.classicmodelsservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
    private Integer id;
    private String name;
    private Double score;
    private Character grade;
}
