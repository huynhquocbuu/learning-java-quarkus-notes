package learning.quarkus.notes.configuration.exceptions;

import io.quarkus.security.AuthenticationFailedException;
import io.vertx.ext.auth.authorization.Authorization;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

@Provider
//@Priority(Priorities.AUTHENTICATION)
@Slf4j
public class AuthenticationFailedExceptionMapper implements ExceptionMapper<AuthenticationFailedException> {
    @Override
    public Response toResponse(AuthenticationFailedException e) {
        //AuthorizationFailException dd;
        log.info("..........AuthenticationFailedException..........\n {}", e);
        return Response
                .status(500)
                //.header("WWW-Authenticate", "Basic realm=\"Quarkus\"")
                .entity("AuthenticationFailedException custom")
                .build();
    }
}
