package com.libraryapp.security.util;

import java.security.SecureRandom;
import java.util.Base64;

public class RandomKeyGeneratior {

    public static String generateRandomKey() {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[64];
        random.nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }

}
