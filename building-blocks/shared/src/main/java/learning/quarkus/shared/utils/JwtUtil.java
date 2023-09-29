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

    public String genJwt(String username, Set<String> roles, String issuer, Long jwtExpirationInSeconds){
        return genRS256Jwt(username, roles, issuer, jwtExpirationInSeconds);
    }

    public String genHS256Jwt(String secretKey, String issuer){
        var output = Jwt
                .issuer(issuer)
                .groups(new HashSet<>(Arrays.asList("User", "Admin")))
                .claim(Claims.birthdate.name(), "2001-07-13")
                .upn("Alice").signWithSecret(secretKey);
        return output;
    }

    public String genRS256JwtFake(String issuer){

        String token = Jwt.issuer(issuer)
                .upn("jdoe@quarkus.io")
                .groups(new HashSet<>(Arrays.asList("User", "Admin")))
                .claim(Claims.birthdate.name(), "2001-07-13")
                .sign();

        return token;
    }

    public String genRS256Jwt(String username, Set<String> roles, String issuer, Long jwtExpirationInSeconds){
        return Jwt.issuer(issuer)
                .upn(username)
                .groups(roles)
                .expiresAt((new Date()).getTime() + jwtExpirationInSeconds)
                .sign();
    }
}
