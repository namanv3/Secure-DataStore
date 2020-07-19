package apicontrol;

import static spark.Spark.*;

public class FileController {
    public FileController (final FileService fileService) {
        String username = fileService.getUsername();

        get("/users/" + username, (req, res) -> {
            String filename = req.queryParams("filename");
            var fileResponse = fileService.getFile(filename);
            if (fileResponse != null) return fileResponse;
            res.status(400);
            return new ResponseError("Incorrect filename. Filename does not exist.");
        },JsonUtil.json());

        post("/users/" + username, (req, res) -> {
            String filename = req.queryParams("filename");
            byte[] data = req.queryParams("data").getBytes();
            var fileResponse = fileService.addFile(filename,data);
            if (fileResponse != null) return fileResponse;
            res.status(400);
            return new ResponseError("Filename already in use.");
        },JsonUtil.json());

        after((req, res) -> res.type("application/json"));
    }
}
