package learning.quarkus.shared.mappings;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "jakarta")
//@Mapper
public interface PersonMapper {
    @Mapping(target = "surname", source = "lastname")
    PersonDto toResource(Person person);
}
