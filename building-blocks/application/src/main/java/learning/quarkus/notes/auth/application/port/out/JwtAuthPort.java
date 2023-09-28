package learning.quarkus.notes.auth.application.port.out;

import learning.quarkus.shared.dtos.UserDto;

import java.util.Optional;

public interface JwtAuthPort {
    Optional<UserDto> localAuthenticate(String username, String password);

}
