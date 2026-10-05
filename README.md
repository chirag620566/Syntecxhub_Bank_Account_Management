# Bank Account Management System (Java Collections)

## Features
- Create account (auto account number, 4-digit PIN)
- Deposit / Withdraw (validation + custom exception)
- View balance, transaction history
- Admin view: all accounts (sorted)

## Collections Used
| Collection | Kahan | Kyun |
|---|---|---|
| `HashMap<String, Account>` | Bank.java | Account number se O(1) lookup |
| `ArrayList<Transaction>` | Account.java | Transactions ka ordered history |
| `Collections.unmodifiableList` | Account.getHistory() | History ko bahar se modify hone se bachata hai |
| `List.sort` + `Comparator` | Bank.getAllAccounts() | Sorted account listing |

## Secure Data Handling
- Encapsulation: saare fields private
- PIN plain text me store nahi hota (hash)
- Har operation se pehle PIN authentication
- Input validation + custom `InsufficientFundsException`
- Immutable `Transaction` objects

## Run
```
cd src
javac bank/*.java
java bank.Main
```
