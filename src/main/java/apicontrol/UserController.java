package apicontrol;

import static spark.Spark.*;

public class UserController {
    public UserController (final UserService userService) {
        get("/", (req,res) -> "Hello");

        get("/users", (req,res) -> {
            String username = req.queryParams("username");
            String password = req.queryParams("password");
            var user = userService.getUser(username, password);
            if (user != null) {
                new FileController(new FileService(userService.getDs(), user));
                return new UserResponseObject(user);
            }
            res.status(400);
            return new ResponseError("Incorrect credentials. Please check your username and password.");
        }, JsonUtil.json());

        post("/users", (req, res) -> {
            String username = req.queryParams("username");
            String password = req.queryParams("password");
            var user = userService.addUser(username, password);
            if (user != null) return new UserResponseObject(user);
            res.status(400);
            return new ResponseError("A user with id " + username + " already exists. Please choose another username.");
        }, JsonUtil.json());

        after((req, res) -> res.type("application/json"));
    }
}