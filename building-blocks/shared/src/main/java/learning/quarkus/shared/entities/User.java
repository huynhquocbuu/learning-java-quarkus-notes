package learning.quarkus.shared.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import learning.quarkus.shared.enums.EAuthType;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", length = 100, unique=true)
    private String username;

    @Column(length = 200)
    private String password;

    @Column(name = "full_name", length = 100)
    private String fullName;

    @Email
    @Column(length = 100)
    private String email;

    @Column(name = "auth_type", length = 20)
    @Enumerated(EnumType.STRING)
    private EAuthType authType;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_roles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private Set<Role> roles = new HashSet<>();

}
