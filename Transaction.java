package bank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Immutable transaction record (history ke liye). */
public final class Transaction {
    public enum Type { OPEN, DEPOSIT, WITHDRAW }

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    private final Type type;
    private final double amount;
    private final double balanceAfter;
    private final LocalDateTime time;

    public Transaction(Type type, double amount, double balanceAfter) {
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.time = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format("%s | %-8s | Amount: %10.2f | Balance: %10.2f",
                time.format(FMT), type, amount, balanceAfter);
    }
}
