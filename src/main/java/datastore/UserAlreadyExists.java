package datastore;

public class UserAlreadyExists extends Exception {
    public UserAlreadyExists(String s) {
        super(s);
    }
}
