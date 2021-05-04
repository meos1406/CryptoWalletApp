package exceptions;

public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException() {
        super("Insufficient Account Balance");
    }
}
