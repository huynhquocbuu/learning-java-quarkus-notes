package learning.quarkus.shared.mappings;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {
    private String firstname;
    private String lastname;
}
