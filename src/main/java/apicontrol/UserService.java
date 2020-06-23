package apicontrol;

import datastore.*;

public class UserService {
    private final IDataStore ds;
    private final IPublicStore ps;

    public UserService(String url, int port, String company) {
        ds = new MongoDBDataStore(url,port,company);
        ps = new MongoDBPublicStore(url,port,company);
    }

    public User addUser(String username, String password) throws Exception {
        User newUser;
        try {
            newUser = ds.initUser(username,password,ps);
        } catch (UserAlreadyExists e) {
            return null;
        }
        return newUser;
    }

    public User getUser(String username, String password) throws Exception {
        User newUser;
        try {
            newUser = ds.getUser(username,password,ps);
        } catch (InvalidUsername | InvalidPassword e) {
            return null;
        }
        return newUser;
    }

    public IDataStore getDs() {
        return ds;
    }
}