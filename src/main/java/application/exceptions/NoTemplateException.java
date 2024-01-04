package application.exceptions;

public final class NoTemplateException extends RuntimeException {
    public NoTemplateException() {
        super("No template provided");
    }
}
