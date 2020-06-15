import datastore.*;
import org.junit.Test;

public class MongoDBDataStoreTest {
    @Test
    public void addInitialUsers () throws Exception {
        IDataStore newDS = new MongoDBDataStore("alphaDrive");
        IPublicStore newPKS = new MongoDBPublicStore("alphaDrive");
        newDS.initUser("robin","goodPasswordsAreHard",newPKS);
        newDS.initUser("chris","goodPasswordsAreHarder",newPKS);
        newDS.initUser("teddy","longPasswordsAreHardestOfThemAll",newPKS);
        newDS.initUser("billy","what?GoodPasswordsExist!?",newPKS);
    }

    @Test
    public void addInitialUsersToNewPlatform () throws Exception {
        IDataStore newDS = new MongoDBDataStore("betaDrive");
        IPublicStore newPKS = new MongoDBPublicStore("betaDrive");
        newDS.initUser("robin","goodPasswordsAreHard",newPKS);
        newDS.initUser("chris","goodPasswordsAreHarder",newPKS);
        newDS.initUser("teddy","longPasswordsAreHardestOfThemAll",newPKS);
        newDS.initUser("billy","what?GoodPasswordsExist!?",newPKS);
        newDS.initUser("sally","ICanOnlyBeFoundOnBetaDrive",newPKS);
    }

    @Test (expected = datastore.InvalidUsername.class)
    public void wrongPlatform () throws Exception {
        IDataStore newDS = new MongoDBDataStore("alphaDrive");
        IPublicStore newPKS = new MongoDBPublicStore("alphaDrive");
        User sally = newDS.getUser("sally","ICanOnlyBeFoundOnBetaDrive",newPKS);
    }

}
