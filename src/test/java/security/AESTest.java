package security;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AESTest {
    @Test
    public void encryptAndDecryptSampleString () throws Exception {
        String contents = "This is a sample byte array. Nothing much here.";
        byte[] data = contents.getBytes();
        assertEquals(contents, new String(data));
        String secretKeyCreator = "anotherRandomStringWhichIsProbablyAPassword";
        byte[] encryptedData = AES.encrypt(data, secretKeyCreator);

        assertNotEquals(data,encryptedData);

        byte[] decryptedData = AES.decrypt(encryptedData,secretKeyCreator);

        assertEquals(contents,new String(decryptedData));
    }
}