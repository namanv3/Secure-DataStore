package apicontrol;

import datastore.User;

public class UserResponseObject {
    private final String username;

    public UserResponseObject(User user) {
        this.username = user.getUsername();
    }
}
