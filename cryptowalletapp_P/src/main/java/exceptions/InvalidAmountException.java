package exceptions;

public class InvalidAmountException extends Exception {
    public InvalidAmountException() {
        super("Invalid Amount < 0!");
    }
}
