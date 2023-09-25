package rest.reactive.sample;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import learning.quarkus.shared.utils.JwtUtil;

@Path("/jwt")
public class JwtResource {
    private JwtUtil jwtUtil;
    public JwtResource(){
        jwtUtil = new JwtUtil();
    }
    @GET
    @Path("/genH256Jwt")
    @Produces(MediaType.TEXT_PLAIN)
    public String get() {
        String secretKey = "secret-key-123-456-789:secret-key-123-456-789:secret-key-123-456-789:secret-key-123-456-789";
        return jwtUtil.genHS256Jwt(secretKey);
    }
}
