package application.exceptions;

public class NoTemplateException extends RuntimeException {
    public NoTemplateException() {
        super("No template provided");
    }
}
