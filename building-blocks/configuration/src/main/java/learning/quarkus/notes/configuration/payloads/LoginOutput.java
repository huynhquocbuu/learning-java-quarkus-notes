package learning.quarkus.notes.configuration.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginOutput {
    private String jwt;
}
