package exceptions;

public class InsufficientAmountException extends Exception {
    public InsufficientAmountException() {
        super("Insufficient Amount of Crypto in Wallet");
    }
}
