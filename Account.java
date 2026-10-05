package bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Ek bank account. Encapsulation: saare fields private, balance sirf
 * deposit()/withdraw() se change hota hai. PIN hash ke form me store hota hai.
 */
public class Account {
    private final String accountNumber;
    private final String holderName;
    private final int pinHash;               // plain PIN store nahi karte
    private double balance;
    private final List<Transaction> history = new ArrayList<>();

    public Account(String accountNumber, String holderName, String pin, double openingBalance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.pinHash = pin.hashCode();
        this.balance = openingBalance;
        history.add(new Transaction(Transaction.Type.OPEN, openingBalance, balance));
    }

    public String getAccountNumber() { return accountNumber; }
    public String getHolderName()    { return holderName; }
    public double getBalance()       { return balance; }

    public boolean verifyPin(String pin) {
        return pin != null && pin.hashCode() == pinHash;
    }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit amount 0 se zyada hona chahiye.");
        balance += amount;
        history.add(new Transaction(Transaction.Type.DEPOSIT, amount, balance));
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) throw new IllegalArgumentException("Withdraw amount 0 se zyada hona chahiye.");
        if (amount > balance)
            throw new InsufficientFundsException(
                    String.format("Insufficient balance! Available: %.2f, Requested: %.2f", balance, amount));
        balance -= amount;
        history.add(new Transaction(Transaction.Type.WITHDRAW, amount, balance));
    }

    /** Read-only view, taaki bahar se history modify na ho sake. */
    public List<Transaction> getHistory() {
        return Collections.unmodifiableList(history);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        return accountNumber.equals(((Account) o).accountNumber);
    }

    @Override
    public int hashCode() { return Objects.hash(accountNumber); }

    @Override
    public String toString() {
        return String.format("A/C No: %s | Holder: %s | Balance: %.2f", accountNumber, holderName, balance);
    }
}
