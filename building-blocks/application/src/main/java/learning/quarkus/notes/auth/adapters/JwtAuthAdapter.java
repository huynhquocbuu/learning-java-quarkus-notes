package learning.quarkus.notes.auth.adapters;

import jakarta.enterprise.context.ApplicationScoped;
import learning.quarkus.notes.auth.application.port.out.JwtAuthPort;
import learning.quarkus.shared.dtos.RoleDto;
import learning.quarkus.shared.dtos.UserDto;
import learning.quarkus.shared.enums.ERole;

import java.util.HashSet;
import java.util.Optional;

@ApplicationScoped
public class JwtAuthAdapter implements JwtAuthPort {
    @Override
    public Optional<UserDto> localAuthenticate(String username, String password) {
        //Code here to modify.
        return localAuthenticateFake(username, password);
    }


    private Optional<UserDto> localAuthenticateFake(String username, String password) {
        if(username.equals("admin") && password.equals("admin")){
            var fakeRoles = new HashSet<ERole>();
            fakeRoles.add(ERole.Admin); fakeRoles.add(ERole.User);
            return Optional.of(UserDto
                    .builder()
                            .username(username)
                            .roles(fakeRoles)
                    .build());
        }

        return Optional.empty();
    }
}
