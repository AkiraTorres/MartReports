package com.poo.MartReports;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Utils {
    public static String encodeString(String str) throws NoSuchAlgorithmException {
        String encodedStr = null;

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        MessageDigest md;
        md = MessageDigest.getInstance("SHA-512");
        md.update(salt);

        byte[] hashedStr = md.digest(str.getBytes(StandardCharsets.UTF_8));
        encodedStr = new String(hashedStr, StandardCharsets.UTF_8);
        return encodedStr;
    }

}
