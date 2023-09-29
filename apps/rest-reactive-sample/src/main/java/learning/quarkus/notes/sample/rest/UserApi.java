package learning.quarkus.notes.sample.rest;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import learning.quarkus.notes.configuration.payloads.ResponseProducer;
import learning.quarkus.notes.configuration.payloads.ResponseWrapper;
import learning.quarkus.notes.sample.application.port.UserUseCase;
import learning.quarkus.shared.dtos.UserDto;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)//response
@RolesAllowed({ "User", "Admin" })
public class UserApi {
    @Inject
    UserUseCase useCase;

    @GET
    @Path("/list")
    //@Consumes(MediaType.APPLICATION_JSON)
    public RestResponse<ResponseWrapper<List<UserDto>>> list() {

        return ResponseProducer.ok(useCase.getAll());

    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public RestResponse<ResponseWrapper<UserDto>> register(@Valid UserDto userDto) {
        var output = useCase.register(userDto);
        return ResponseProducer.ok(output);

    }
}
