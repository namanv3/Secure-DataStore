package datastore;

import org.junit.Test;

import static org.junit.Assert.*;

public class MapDataStoreTest {
    private final IDataStore   ds = new MapDataStore();
    private final IPublicStore ps = new MapPublicStore();

    @Test
    public void testRandomGenerator () {
        String random1 = ds.generateFileEncryptionKey();
        String random2 = ds.generateFileEncryptionKey();
        assertNotEquals(random1,random2);
    }

    @Test
    public void signUpAndLogin () throws Exception {
        String username = "namanv3";
        String password = "ThisIsNot#@@@#(#)@@#!>:{>{AGoodPassword.OrMaybeItIs$&@(@)@@#>??;./][<.```~";
        ds.initUser(username,password,ps);

        User user = null;
        try {
            user = ds.getUser(username,password,ps);
        } catch (Exception e) {
            fail();
        }
        assertNotNull(user);
        assertEquals(user.getUsername(), username);
    }
}