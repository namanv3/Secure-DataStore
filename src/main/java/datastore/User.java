package datastore;

import security.AES;
import security.HashFunctions;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

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

    public void storeFile (String filename, byte[] data, IDataStore ds) throws IOException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, FilenameAlreadyExists {
        String dataStoreKeyFileBlock = HashFunctions.hashSHA256(filename + this.username);
        if (ds.isPresent(dataStoreKeyFileBlock)) throw new FilenameAlreadyExists("This filename is already in use.");

        FileData dataBlock = new FileData(data);
        byte[] dataBlockBytes = dataBlock.serialize();
        String dataBlockEncryptionString = randomString(16);
        byte[] encryptedDataBlock = AES.encrypt(dataBlockBytes,dataBlockEncryptionString);
        String dataBlockDSKey = randomString(40);
        ds.dataStoreSet(dataBlockDSKey,encryptedDataBlock);

        FileMetadata currFileBlock = new FileMetadata(filename,dataBlockDSKey,dataBlockEncryptionString);
        byte[] fileBlockBytes = currFileBlock.serialize();
        byte[] encryptedFileBlock = AES.encrypt(fileBlockBytes,this.fileEncryptionString);
        ds.dataStoreSet(dataStoreKeyFileBlock,encryptedFileBlock);
    }

    public byte[] loadFile (String filename, IDataStore ds) throws IOException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidFilename {
        String dataStoreKeyFileBlock = HashFunctions.hashSHA256(filename + this.username);
        if (!ds.isPresent(dataStoreKeyFileBlock)) throw new InvalidFilename("Filename does not exist");
        byte[] encryptedFileBlock = ds.dataStoreGet(dataStoreKeyFileBlock);
        byte[] fileBlockBytes = AES.decrypt(encryptedFileBlock,this.fileEncryptionString);
        FileMetadata fileBlock = (FileMetadata) DataStoreBlocks.deserialize(fileBlockBytes);

        assert fileBlock != null;
        String dataBlockDSKey = fileBlock.getDataDSKey();
        byte[] encryptedDataBlock = ds.dataStoreGet(dataBlockDSKey);
        byte[] dataBlockBytes = AES.decrypt(encryptedDataBlock,fileBlock.getDataDecryptionKey());
        FileData dataBlock = (FileData) DataStoreBlocks.deserialize(dataBlockBytes);
        return dataBlock.getContents();
    }

    private String randomString(int length) {
        byte[] array = new byte[length];
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }
}
