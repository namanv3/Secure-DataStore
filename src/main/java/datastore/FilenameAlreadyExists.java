package datastore;

public class FilenameAlreadyExists extends Exception {
    public FilenameAlreadyExists(String message) {
        super(message);
    }
}