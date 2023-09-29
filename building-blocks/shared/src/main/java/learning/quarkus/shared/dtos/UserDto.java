package learning.quarkus.shared.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import learning.quarkus.shared.enums.ERole;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    @JsonProperty("user-name")
    @NotEmpty
    private String username;
    @JsonProperty("full-name")
    private String fullName;
    @Email
    @NotEmpty
    private String email;
    private Set<ERole> roles;
}
