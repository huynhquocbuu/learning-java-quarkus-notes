package learning.quarkus.notes.auth.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import learning.quarkus.notes.auth.application.port.in.LoginUseCase;
import learning.quarkus.notes.auth.application.port.out.JwtAuthPort;
import learning.quarkus.notes.auth.domain.models.LoginInput;
import learning.quarkus.notes.auth.domain.models.LoginOutput;
import learning.quarkus.shared.utils.JwtUtil;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class LoginService implements LoginUseCase {
    @ConfigProperty(name = "mp.jwt.verify.issuer")
    String issuer;
    @ConfigProperty(name = "jwt.expiration.in.seconds")
    Long jwtExpirationInSeconds;

    @Inject
    JwtAuthPort jwtAuthPort;
    @Inject
    JwtUtil jwtUtil;
    @Override
    public Optional<LoginOutput> login(LoginInput input) {
        var userDtoOptional = jwtAuthPort
                .localAuthenticate(
                        input.getUsername(),
                        input.getPassword());
        if(userDtoOptional.isPresent()){
            return Optional.of(LoginOutput
                    .builder()
                            .username(userDtoOptional.get().getUsername())
                            .jwt(jwtUtil.genJwt(
                                    userDtoOptional.get().getUsername(),
                                    userDtoOptional.get().getRoles()
                                            .stream()
                                            .map(m -> m.name())
                                            .collect(Collectors.toSet()),
                                    issuer,
                                    jwtExpirationInSeconds
                                    )
                            )
                    .build());
        }
        return Optional.empty();
    }
}
