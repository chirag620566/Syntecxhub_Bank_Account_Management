package bank;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Bank service class.
 * HashMap<String, Account> use kiya hai kyunki account number se lookup O(1) me hota hai.
 */
public class Bank {
    private final Map<String, Account> accounts = new HashMap<>();
    private int nextAccountId = 1001;

    /** Naya account create karta hai aur account number return karta hai. */
    public String createAccount(String name, String pin, double openingBalance) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name khali nahi ho sakta.");
        if (!pin.matches("\\d{4}"))
            throw new IllegalArgumentException("PIN exactly 4 digits ka hona chahiye.");
        if (openingBalance < 0)
            throw new IllegalArgumentException("Opening balance negative nahi ho sakta.");

        String accNo = "ACC" + nextAccountId++;
        accounts.put(accNo, new Account(accNo, name.trim(), pin, openingBalance));
        return accNo;
    }

    /** PIN verify karke account return karta hai (security check). */
    private Account authenticate(String accNo, String pin) {
        Account acc = accounts.get(accNo);
        if (acc == null)
            throw new IllegalArgumentException("Account nahi mila: " + accNo);
        if (!acc.verifyPin(pin))
            throw new SecurityException("Galat PIN!");
        return acc;
    }

    public void deposit(String accNo, String pin, double amount) {
        authenticate(accNo, pin).deposit(amount);
    }

    public void withdraw(String accNo, String pin, double amount) throws InsufficientFundsException {
        authenticate(accNo, pin).withdraw(amount);
    }

    public double viewBalance(String accNo, String pin) {
        return authenticate(accNo, pin).getBalance();
    }

    public List<Transaction> getHistory(String accNo, String pin) {
        return authenticate(accNo, pin).getHistory();
    }

    /** Admin view: saare accounts, account number ke order me sorted. */
    public List<Account> getAllAccounts() {
        List<Account> list = new ArrayList<>(accounts.values());
        list.sort(Comparator.comparing(Account::getAccountNumber));
        return list;
    }
}
