package datastore;

public class InvalidPassword extends Exception{
    public InvalidPassword() {
        super("Password is incorrect.");
    }
}
