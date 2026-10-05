package bank;

/** Custom checked exception: balance se zyada withdraw karne par throw hoti hai. */
public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
