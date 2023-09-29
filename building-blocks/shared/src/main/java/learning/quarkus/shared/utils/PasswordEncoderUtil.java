package learning.quarkus.shared.utils;

import jakarta.inject.Singleton;
import org.mindrot.jbcrypt.BCrypt;

@Singleton
public class PasswordEncoderUtil {
    public String hash(String plaintext){
        return BCrypt.hashpw(plaintext, BCrypt.gensalt(12));
    }

    public boolean verifyHash(String plaintext, String hashed){
        return BCrypt.checkpw(plaintext, hashed);
    }
}
