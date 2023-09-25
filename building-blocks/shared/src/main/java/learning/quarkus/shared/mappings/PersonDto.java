package learning.quarkus.shared.mappings;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonDto {
    private String firstname;
    private String surname;
}
