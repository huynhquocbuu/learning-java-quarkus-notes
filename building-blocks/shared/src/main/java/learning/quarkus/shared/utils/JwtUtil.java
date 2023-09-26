package learning.quarkus.shared.utils;

import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.jwt.Claims;

import java.util.Arrays;
import java.util.HashSet;

public class JwtUtil {
//    public String genSimpleJwt(){
//        return Jwt
//                .issuer("https://example.com/issuer")
//                .upn("jdoe@quarkus.io")
//                .groups(new HashSet<>(Arrays.asList("User", "Admin")))
//                .claim(Claims.birthdate.name(), "2001-07-13")
//                .sign();
//    }

    public String genHS256Jwt(String secretKey){
        var output = Jwt
                .issuer("tpb.fico.dev")
                .groups(new HashSet<>(Arrays.asList("User", "Admin")))
                .claim(Claims.birthdate.name(), "2001-07-13")
                .upn("Alice").signWithSecret(secretKey);
        return output;
    }

    public String genRS256Jwt(){

        String token = Jwt.issuer("https://example.com/issuer")
                .upn("jdoe@quarkus.io")
                .groups(new HashSet<>(Arrays.asList("User", "Admin")))
                .claim(Claims.birthdate.name(), "2001-07-13")
                .sign();

        return token;
    }
}
