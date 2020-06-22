import apicontrol.UserController;
import apicontrol.UserService;

public class Main {
    public static void main(String[] args) {
        new UserController(new UserService(args[0], Integer.parseInt(args[1]), "gDrive"));
    }
}
