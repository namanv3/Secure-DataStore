import apicontrol.UserController;
import apicontrol.UserService;

public class Main {
    public static void main(String[] args) {
        new UserController(new UserService("gDrive"));
    }
}
