package datastore;

public class User extends DataStoreBlocks {
    private final String username;
    private final String fileEncryptionString;

    public User(String username, String fileEncryptionString) {
        this.username = username;
        this.fileEncryptionString = fileEncryptionString;
    }

    public String getUsername() {
        return username;
    }
}
