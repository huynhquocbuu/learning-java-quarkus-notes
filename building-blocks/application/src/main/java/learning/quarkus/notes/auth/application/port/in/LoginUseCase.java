package learning.quarkus.notes.auth.application.port.in;

import learning.quarkus.notes.auth.domain.models.LoginInput;
import learning.quarkus.notes.auth.domain.models.LoginOutput;

import java.util.Optional;

public interface LoginUseCase {
    Optional<LoginOutput> login(LoginInput input);
}
