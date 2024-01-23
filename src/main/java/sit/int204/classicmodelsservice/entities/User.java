package sit.int204.classicmodelsservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    private String name;
    private String password;
}
