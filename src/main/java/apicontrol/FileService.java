package apicontrol;

import datastore.FilenameAlreadyExists;
import datastore.IDataStore;
import datastore.InvalidFilename;
import datastore.User;

public class FileService {
    private final IDataStore ds;
    private final User user;

    public FileService(IDataStore ds, User user) {
        this.ds = ds;
        this.user = user;
    }

    public String getUsername () {
        return user.getUsername();
    }

    public FilenameResponseObject addFile (String filename, byte[] data) throws Exception {
        try {
            user.storeFile(filename, data, ds);
        } catch (FilenameAlreadyExists e) {
            return null;
        }
        return new FilenameResponseObject(filename);
    }

    public FileResponseObject getFile (String filename) throws Exception {
        byte[] data;
        try {
            data = user.loadFile(filename,ds);
        } catch (InvalidFilename e) {
            return null;
        }
        return new FileResponseObject(filename, new String(data));
    }
}
