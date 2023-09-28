package learning.quarkus.shared.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Role {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    //private ERole id;
    private Long id;
    private String roleName;
    private String roleDescription;

}
