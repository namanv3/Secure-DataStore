import datastore.*;
import org.junit.Test;

public class MapDataStoreTest {
    @Test
    public void addNewUser() throws Exception {
        IDataStore newDS = new MapDataStore();
        IPublicStore newPKS = new MapPublicStore();
        newDS.initUser("robin","goodPasswordsAreHard",newPKS);
    }

    @Test
    public void addNewUserLongPassword() throws Exception {
        IDataStore newDS = new MapDataStore();
        IPublicStore newPKS = new MapPublicStore();
        newDS.initUser("robin","goodPasswordsAreHard.PleaseUnderstandMyLogic.UseSpecialCharacters1122!!@@##______.==",newPKS);
    }

    @Test (expected = datastore.UserAlreadyExists.class)
    public void addUserExistingUsername() throws Exception {
        IDataStore newDS = new MapDataStore();
        IPublicStore newPKS = new MapPublicStore();
        newDS.initUser("robin","goodPasswordsAreHard",newPKS);
        newDS.initUser("robin","foodPasswordsAreEasy",newPKS);
    }

    @Test
    public void getAddedUser() throws Exception {
        IDataStore newDS = new MapDataStore();
        IPublicStore newPKS = new MapPublicStore();
        newDS.initUser("robin","goodPasswordsAreHard",newPKS);
        User robin = newDS.getUser("robin","goodPasswordsAreHard",newPKS);
    }

    @Test (expected = datastore.InvalidUsername.class)
    public void wrongUsername() throws Exception {
        IDataStore newDS = new MapDataStore();
        IPublicStore newPKS = new MapPublicStore();
        newDS.initUser("robin","goodPasswordsAreHard",newPKS);
        User robin = newDS.getUser("ruben","goodPasswordsAreHard",newPKS);
    }

    @Test (expected = datastore.InvalidPassword.class)
    public void wrongPassword() throws Exception {
        IDataStore newDS = new MapDataStore();
        IPublicStore newPKS = new MapPublicStore();
        newDS.initUser("robin","goodPasswordsAreHard",newPKS);
        User robin = newDS.getUser("robin","goodPasswordsAreEasy",newPKS);
    }
}
