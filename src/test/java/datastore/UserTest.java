package datastore;

import org.junit.Test;
import spark.utils.IOUtils;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

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

    @Test
    public void StoreAndGetExecutable () throws Exception {
        String filename = "a.out";
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(filename)) {
            assert inputStream != null;
            String contents = IOUtils.toString(inputStream);

            user.storeFile(filename,contents.getBytes(),ds);

            String loadedContents = new String(user.loadFile(filename,ds));
            assertEquals(contents,loadedContents);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}