package learning.quarkus.notes.rest;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import learning.quarkus.shared.mappings.Person;
import learning.quarkus.shared.mappings.PersonDto;
import learning.quarkus.shared.mappings.PersonMapper;

@Path("/hello")
//@RolesAllowed({ "User", "Admin" })
public class GreetingResource {
    @Inject
    PersonMapper personMapper;
    @RolesAllowed({ "User", "Admin" })
    @GET
    @Path("/get")
    @Produces(MediaType.TEXT_PLAIN)
    public String get() {
        return "Hello from RESTEasy Reactive";
    }

    @GET
    @Path("/person")
    @Produces(MediaType.APPLICATION_JSON)
    public PersonDto person() {
        var person = new Person("Bob", "Miller");
        var personDto = personMapper.toResource(person);
        return personDto;
    }
}
