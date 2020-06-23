package datastore;

public class FileMetadata extends DataStoreBlocks {
    private final String filename;
    private final String dataDSKey;
    private final String dataDecryptionKey;

    public FileMetadata(String filename, String dataDSKey, String dataDecryptionKey) {
        this.filename = filename;
        this.dataDSKey = dataDSKey;
        this.dataDecryptionKey = dataDecryptionKey;
    }
    public String getDataDSKey() {
        return dataDSKey;
    }

    public String getDataDecryptionKey() {
        return dataDecryptionKey;
    }


    public String getFilename() {
        return filename;
    }
}
