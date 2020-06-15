package security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class HashFunctions {
    public static String hashSHA256(String s) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] data =  s.getBytes();
        md.update(data);
        byte[] digest = md.digest();
        return Arrays.toString(digest);
    }
}
