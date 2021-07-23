package io.muic.ssc.WeFit.utilities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptorUtils {

    /**
     * Encrypt user's password
     * @param password
     * @return Password that already encrypt
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
