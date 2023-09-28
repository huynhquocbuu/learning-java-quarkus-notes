package learning.quarkus.shared.utils;

import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Singleton;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.Claims;
import org.jboss.logging.annotations.Properties;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Singleton
public class JwtUtil {
    @ConfigProperty(name = "jwt.issuer")
    String issuer;
    @ConfigProperty(name = "jwt.expiration.in.seconds")
    Long jwtExpirationInSeconds;
//    public String genSimpleJwt(){
//        return Jwt
//                .issuer("https://example.com/issuer")
//                .upn("jdoe@quarkus.io")
//                .groups(new HashSet<>(Arrays.asList("User", "Admin")))
//                .claim(Claims.birthdate.name(), "2001-07-13")
//                .sign();
//    }

    public String genJwt(String username, Set<String> roles){
        return genRS256Jwt(username, roles);
    }

    public String genHS256Jwt(String secretKey){
        var output = Jwt
                .issuer("tpb.fico.dev")
                .groups(new HashSet<>(Arrays.asList("User", "Admin")))
                .claim(Claims.birthdate.name(), "2001-07-13")
                .upn("Alice").signWithSecret(secretKey);
        return output;
    }

    public String genRS256JwtFake(){

        String token = Jwt.issuer("https://example.com/issuer")
                .upn("jdoe@quarkus.io")
                .groups(new HashSet<>(Arrays.asList("User", "Admin")))
                .claim(Claims.birthdate.name(), "2001-07-13")
                .sign();

        return token;
    }

    public String genRS256Jwt(String username, Set<String> roles){
        var aaa = (new Date()).getTime() + jwtExpirationInSeconds;
        return Jwt.issuer(issuer)
                .upn(username)
                .groups(roles)
                .expiresAt((new Date()).getTime() + jwtExpirationInSeconds)
                .sign();
    }
}
