package apicontrol;

import datastore.*;

public class UserService {
    private final IDataStore ds;
    private final IPublicStore ps;

    public UserService(String company) {
        ds = new MongoDBDataStore(company);
        ps = new MongoDBPublicStore(company);
    }

    public User addUser(String username, String password) throws Exception {
        return ds.initUser(username,password,ps);
    }

    public User getUser(String username, String password) throws Exception {
        return ds.getUser(username,password,ps);
    }
}