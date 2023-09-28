package learning.quarkus.notes.auth.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import learning.quarkus.notes.auth.application.port.in.LoginUseCase;
import learning.quarkus.notes.auth.domain.models.LoginInput;
import learning.quarkus.notes.auth.domain.models.LoginOutput;
import learning.quarkus.notes.configuration.payloads.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/auth")
@Slf4j
public class AuthApi {

    @Inject
    LoginUseCase loginUseCase;

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RestResponse<ResponseWrapper<LoginOutput>> login(LoginInput input){
        var loginRsOptional = loginUseCase.login(input);
        return loginRsOptional.isPresent()
                ? RestResponse.ResponseBuilder
                    .create(
                            RestResponse.Status.OK,
                            new ResponseWrapper<LoginOutput>(
                                    "success", "", "",
                                    new LoginOutput(
                                            loginRsOptional.get().getUsername(),
                                            loginRsOptional.get().getJwt()))
                    )
                    .type(MediaType.APPLICATION_JSON)
                    .build()
                : RestResponse.ResponseBuilder
                    .create(
                            RestResponse.Status.OK,
                            new ResponseWrapper<LoginOutput>(
                                    "fail", "E001", "invalid username or password",
                                    null)
                    )
                    .type(MediaType.APPLICATION_JSON)
                    .build();

    }

}