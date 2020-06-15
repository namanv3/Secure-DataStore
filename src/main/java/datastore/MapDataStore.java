package datastore;

import java.util.HashMap;
import java.util.Map;

public class MapDataStore implements IDataStore{
    private Map<String,byte[]> m;

    public MapDataStore() {
        m = new HashMap<>();
    }

    @Override
    public void dataStoreSet(String key, byte[] data) {
        m.put(key,data);
    }

    @Override
    public byte[] dataStoreGet(String key) {
        return m.get(key);
    }

    @Override
    public boolean isPresent(String key) {
        return m.containsKey(key);
    }
}
