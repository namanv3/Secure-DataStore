package datastore;

public class FileData extends DataStoreBlocks {
    private final byte[] contents;

    public FileData(byte[] contents) {
        this.contents = contents;
    }

    public byte[] getContents() {
        return contents;
    }
}
