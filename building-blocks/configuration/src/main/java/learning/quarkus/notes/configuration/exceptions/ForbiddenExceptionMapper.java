package learning.quarkus.notes.configuration.exceptions;

import io.quarkus.security.ForbiddenException;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

@Provider
@Slf4j
@Priority(Priorities.AUTHORIZATION)
public class ForbiddenExceptionMapper implements ExceptionMapper<ForbiddenException> {
    @Override
    public Response toResponse(ForbiddenException e) {
        log.info("ForbiddenException {} ", e);
        return Response.status(Response.Status.FORBIDDEN)
                .entity("Sorry access forbidden")
                .build();
    }
}
