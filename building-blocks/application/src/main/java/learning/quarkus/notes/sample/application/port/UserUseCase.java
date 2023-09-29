package learning.quarkus.notes.sample.application.port;

import learning.quarkus.shared.dtos.UserDto;

import java.util.List;

public interface UserUseCase {
    List<UserDto> getAll();
    UserDto register(UserDto userDto);
}
