package security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AES {
    public static byte[] encrypt(byte[] data, String secretKeyBase) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] secretKeyBaseBytes =  secretKeyBase.getBytes();
        md.update(secretKeyBaseBytes);
        byte[] digest = md.digest();
        Key encryptionKey = new SecretKeySpec(digest, "AES");

        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.ENCRYPT_MODE, encryptionKey);
        return aesCipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] data, String secretKeyBase) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] secretKeyBaseBytes =  secretKeyBase.getBytes();
        md.update(secretKeyBaseBytes);
        byte[] digest = md.digest();
        Key decryptionKey = new SecretKeySpec(digest, "AES");

        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.DECRYPT_MODE, decryptionKey);
        return aesCipher.doFinal(data);
    }
}
