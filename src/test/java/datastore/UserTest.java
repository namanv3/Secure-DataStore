package datastore;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    private final IDataStore ds = new MapDataStore();
    private final User user = new User("namanv3","badPassword1!2@.,.,,...><><<><>");

    @Test
    public void StoreAndGetTextFile () throws Exception {
        String filename = "howToSetUpClickAPhoto.txt";
        String contents = "1. Take out your phone.\n2. Unlock your phone.\n3.Open Camera App.\n4. Tap Shutter Button.";

        user.storeFile(filename,contents.getBytes(),ds);

        String loadedContents = new String(user.loadFile(filename,ds));

        assertEquals(contents,loadedContents);
    }
}