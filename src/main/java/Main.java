import apicontrol.UserController;
import apicontrol.UserService;

public class Main {
    public static void main(String[] args) {
        String hostURL = System.getenv("MONGODB_HOST");
        int hostPort = Integer.parseInt(System.getenv("MONGODB_PORT"));
        new UserController(new UserService(hostURL, hostPort, "gDrive"));
    }
}
