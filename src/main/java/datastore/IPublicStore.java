package datastore;

public interface IPublicStore {
    void    publicStoreSet (String key, byte[] data);
    byte[]  publicStoreGet (String key);
    boolean isPresent(String key);
}
