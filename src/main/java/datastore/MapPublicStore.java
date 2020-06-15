package datastore;

import java.util.HashMap;
import java.util.Map;

public class MapPublicStore implements IPublicStore {
    private Map<String,byte[]> m;

    public MapPublicStore() {
        m = new HashMap<>();
    }

    @Override
    public void publicStoreSet(String key, byte[] data) {
        m.put(key,data);
    }

    @Override
    public byte[] publicStoreGet(String key) {
        return m.get(key);
    }

    @Override
    public boolean isPresent(String key) {
        return m.containsKey(key);
    }
}
