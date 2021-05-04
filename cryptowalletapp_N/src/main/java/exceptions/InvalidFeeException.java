package exceptions;

public class InvalidFeeException extends Exception {
    public InvalidFeeException() {
        super("Invalid Fee!");
    }
}
