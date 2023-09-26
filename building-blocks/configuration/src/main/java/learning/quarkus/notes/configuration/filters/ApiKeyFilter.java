package learning.quarkus.notes.configuration.filters;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.reactive.RestResponse;

import java.io.IOException;

/*
@Provider
@Slf4j
public class ApiKeyFilter implements ContainerRequestFilter {
    @ConfigProperty(name = "security.x-api-key")
    String xApiKey;
    @Override
    public void filter(ContainerRequestContext context)
            throws IOException {
        String headerXApiKey = context.getHeaderString("x-api-key");
        if(!xApiKey.equals(headerXApiKey)){
            var response = Response
                    .status(RestResponse.Status.BAD_REQUEST)
                    .type(MediaType.TEXT_PLAIN_TYPE)
                    .entity("invalid api key")
                    .build();
            context.abortWith(response);
        }
    }
}
*/