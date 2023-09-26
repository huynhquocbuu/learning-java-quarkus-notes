package learning.quarkus.notes.rest;

import io.quarkus.vertx.web.Body;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import learning.quarkus.notes.configuration.payloads.LoginInput;
import learning.quarkus.notes.configuration.payloads.LoginOutput;
import learning.quarkus.shared.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;

@Path("/jwt")
@Slf4j
public class JwtResource {
    private JwtUtil jwtUtil;
    public JwtResource(){
        jwtUtil = new JwtUtil();
    }
    @GET
    @Path("/genHS256Jwt")
    @Produces(MediaType.TEXT_PLAIN)
    public String get() {
        String secretKey = "secret-key-123-456-789:secret-key-123-456-789:secret-key-123-456-789:secret-key-123-456-789";
        //return jwtUtil.genHS256Jwt(secretKey);
        return jwtUtil.genRS256Jwt();
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public LoginOutput login(LoginInput input){
        //log.info("input = {}", input);
        return new LoginOutput(jwtUtil.genRS256Jwt());
    }

}
