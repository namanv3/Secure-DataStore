package apicontrol;

import static spark.Spark.*;

public class UserController {
    public UserController (final UserService userService) {
        get("/", (req,res) -> "Hello");

        get("/users", (req,res) -> {
            String username = req.queryParams("username");
            String password = req.queryParams("password");
            return userService.getUser(username, password);
        }, JsonUtil.json());

        post("/users", (req, res) -> {
            String username = req.queryParams("username");
            String password = req.queryParams("password");
            System.out.println("username is " + username + ". password is " + password + ".\n\n\n\n\n\n\n");
            return userService.addUser(username, password);
        }, JsonUtil.json());

        after((req, res) -> res.type("application/json"));
    }
}
