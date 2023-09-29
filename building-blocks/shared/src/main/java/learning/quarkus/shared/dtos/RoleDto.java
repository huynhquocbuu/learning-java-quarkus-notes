package learning.quarkus.shared.dtos;

import learning.quarkus.shared.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDto {
    private ERole id;
    private String name;
}
