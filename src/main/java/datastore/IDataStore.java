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

public interface IDataStore {
    void    dataStoreSet (String key, byte[] data);
    byte[]  dataStoreGet (String key);
    boolean isPresent(String key);


    default User initUser (String username, String password, IPublicStore pks) throws UserAlreadyExists, IOException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        boolean alreadyExists = pks.isPresent(username);
        if (alreadyExists) throw new UserAlreadyExists(username + " already exists. Try new username");
        pks.publicStoreSet(username,new byte[0]);

        User newUser = new User(username,generateFileEncryptionKey());
        byte[] newUserBytes = newUser.serialize();
        byte[] userEnc = AES.encrypt(newUserBytes,password);

        String dataStoreKey = HashFunctions.hashSHA256(password+username);  // need to update this

        dataStoreSet(dataStoreKey, userEnc);

        return newUser;
    }

    default User getUser (String username, String password, IPublicStore pks) throws InvalidUsername, NoSuchAlgorithmException, InvalidPassword {
        boolean isPresent = pks.isPresent(username);
        if (!isPresent) throw new InvalidUsername(username + " does not exist.");

        String dataStoreKey = HashFunctions.hashSHA256(password+username);
        if (!this.isPresent(dataStoreKey)) throw new InvalidPassword();

        byte[] userEnc = dataStoreGet(dataStoreKey);

        byte[] userBytes;
        try {
            userBytes = AES.decrypt(userEnc,password);
            return (User) DataStoreBlocks.deserialize(userBytes);
        } catch (NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            // error that user block is contaminated
            e.printStackTrace();
        }
        return null;
    }

    private String generateFileEncryptionKey() {
        int length = 40;
        byte[] array = new byte[length];
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }
}
