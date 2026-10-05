package bank;

import java.util.List;
import java.util.Scanner;

/** Console menu-driven application. */
public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final Bank bank = new Bank();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== BANK ACCOUNT MANAGEMENT =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Balance");
            System.out.println("5. Transaction History");
            System.out.println("6. View All Accounts (Admin)");
            System.out.println("7. Exit");
            System.out.print("Choice: ");

            String choice = sc.nextLine().trim();
            try {
                switch (choice) {
                    case "1": create(); break;
                    case "2": deposit(); break;
                    case "3": withdraw(); break;
                    case "4": balance(); break;
                    case "5": history(); break;
                    case "6": all(); break;
                    case "7": System.out.println("Thank you! Bye."); return;
                    default:  System.out.println("Invalid choice, dobara try karo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Amount number me daalo.");
            } catch (InsufficientFundsException | IllegalArgumentException | SecurityException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static String ask(String label) {
        System.out.print(label);
        return sc.nextLine().trim();
    }

    private static void create() {
        String name = ask("Holder name: ");
        String pin = ask("Set 4-digit PIN: ");
        double opening = Double.parseDouble(ask("Opening balance: "));
        String accNo = bank.createAccount(name, pin, opening);
        System.out.println("Account created! Aapka Account Number: " + accNo);
    }

    private static void deposit() {
        String acc = ask("Account number: ");
        String pin = ask("PIN: ");
        double amt = Double.parseDouble(ask("Deposit amount: "));
        bank.deposit(acc, pin, amt);
        System.out.println("Deposit successful. New balance: " + bank.viewBalance(acc, pin));
    }

    private static void withdraw() throws InsufficientFundsException {
        String acc = ask("Account number: ");
        String pin = ask("PIN: ");
        double amt = Double.parseDouble(ask("Withdraw amount: "));
        bank.withdraw(acc, pin, amt);
        System.out.println("Withdraw successful. New balance: " + bank.viewBalance(acc, pin));
    }

    private static void balance() {
        String acc = ask("Account number: ");
        String pin = ask("PIN: ");
        System.out.printf("Current balance: %.2f%n", bank.viewBalance(acc, pin));
    }

    private static void history() {
        String acc = ask("Account number: ");
        String pin = ask("PIN: ");
        bank.getHistory(acc, pin).forEach(System.out::println);
    }

    private static void all() {
        List<Account> list = bank.getAllAccounts();
        if (list.isEmpty()) System.out.println("Koi account nahi hai.");
        else list.forEach(System.out::println);
    }
}
