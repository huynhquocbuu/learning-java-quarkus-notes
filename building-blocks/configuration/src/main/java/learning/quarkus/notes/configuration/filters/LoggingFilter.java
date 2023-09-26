package learning.quarkus.notes.configuration.filters;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;


@Provider
@Slf4j
public class LoggingFilter implements
        ContainerRequestFilter, ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if(isJson(requestContext)){
            String jsonRequest = IOUtils.toString(requestContext.getEntityStream(), Charsets.UTF_8);
            log.info("{} url: {}, \n request: {}",
                    requestContext.getMethod(),
                    requestContext.getUriInfo().getPath(),
                    jsonRequest);
            InputStream in = IOUtils.toInputStream(jsonRequest);
            requestContext.setEntityStream(in);
        }else{
            log.info("{} url: {}",
                    requestContext.getMethod(),
                    requestContext.getUriInfo().getPath());
        }
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        log.info("{} url: {}, \n response: {}",
                requestContext.getMethod(),
                requestContext.getUriInfo().getPath(),
                responseContext.getEntity());
    }

    boolean isJson(ContainerRequestContext request) {
        // define rules when to read body
        if (request.getMediaType() !=null){
            return request.getMediaType().toString().equals("application/json");
        }
        return false;
    }
}
