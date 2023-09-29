package learning.quarkus.shared.entities;

import jakarta.persistence.*;
import learning.quarkus.shared.enums.ERole;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
public class Role {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private ERole id;
    //private Long id;

    @Column(name = "role_name", length = 100)
    private String name;
    @Column(name = "role_desc", length = 200)
    private String desc;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
}
