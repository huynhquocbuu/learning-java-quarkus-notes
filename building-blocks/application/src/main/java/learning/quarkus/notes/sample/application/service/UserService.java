package learning.quarkus.notes.sample.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import learning.quarkus.notes.configuration.pesistences.repositories.RoleRepository;
import learning.quarkus.notes.configuration.pesistences.repositories.UserRepository;
import learning.quarkus.notes.sample.application.port.UserUseCase;
import learning.quarkus.shared.dtos.RoleDto;
import learning.quarkus.shared.dtos.UserDto;
import learning.quarkus.shared.entities.Role;
import learning.quarkus.shared.entities.User;
import learning.quarkus.shared.enums.EAuthType;
import learning.quarkus.shared.enums.ERole;
import learning.quarkus.shared.utils.PasswordEncoderUtil;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class UserService implements UserUseCase {
    @ConfigProperty(name = "app.admin.password.default")
    String passwordDefault;

    @Inject
    PasswordEncoderUtil passwordEncoderUtil;
    @Inject
    UserRepository userRepository;
    @Inject
    RoleRepository roleRepository;

    @Override
    public List<UserDto> getAll() {
        List<UserDto> output = new ArrayList<>();
        userRepository.getAll().stream()
                .map(m -> output.add(toUserDto(m)));
        return output;
    }

    @Override
    @Transactional
    public UserDto register(UserDto userDto) {
        User entity = toUser(userDto);
        entity = userRepository.add(entity);
        return userDto;
    }

    private UserDto toUserDto(User user){
        Set<ERole> roles = new HashSet<>();
        user.getRoles()
                .stream()
                .map(m -> roles.add(m.getId()));

        return UserDto
                .builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .roles(roles)
                .build();
    }

    private User toUser(UserDto userDto){
        Set<Role> roles = new HashSet();
        for (ERole roleId : userDto.getRoles()) {
            Role roleEntity = roleRepository.findById(roleId);
            roles.add(roleEntity);
        }
        String defaultPassword = passwordEncoderUtil.hash(passwordDefault);
        return User
                .builder()
                .username(userDto.getUsername())
                .fullName(userDto.getFullName())
                .email(userDto.getEmail())
                .authType(EAuthType.LOCAL)
                .password(defaultPassword)
                .roles(roles)
                .build();
    }


}
