package apicontrol;

public class ResponseError {
    private final String message;

    public ResponseError (String msg, String... args) {
        this.message = String.format(msg, (Object) args);
    }

    public ResponseError(Exception e) {
        this.message = e.getMessage();
    }

    public String getMessage() {
        return this.message;
    }
}
