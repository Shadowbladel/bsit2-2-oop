import java.util.ArrayList;
import java.util.List;

public class Account {

    private final String owner;
    private double balance;
    private final String pin;

    private final List<String> transactionHistory;

    public Account(String owner, double openingBalance, String pin) {

        if (owner == null || owner.isBlank()) {
            throw new IllegalArgumentException(
                    "Account owner cannot be empty."
            );
        }

        if (!Double.isFinite(openingBalance) || openingBalance < 0) {
            throw new IllegalArgumentException(
                    "Opening balance cannot be negative."
            );
        }

        if (!isValidPin(pin)) {
            throw new IllegalArgumentException(
                    "PIN must contain exactly 4 digits."
            );
        }

        this.owner = owner;
        this.balance = openingBalance;
        this.pin = pin;

        transactionHistory = new ArrayList<>();

        transactionHistory.add(
                String.format(
                        "Account opened with balance: %.2f",
                        openingBalance
                )
        );
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public boolean verifyPin(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public void deposit(double amount) {

        try {
            validateAmount(amount);

            balance += amount;

            String transaction = String.format(
                    "Deposit: +%.2f | Balance: %.2f",
                    amount,
                    balance
            );

            transactionHistory.add(transaction);

            System.out.printf(
                    "Deposited %.2f. New balance: %.2f%n",
                    amount,
                    balance
            );

        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Deposit :" + e.getMessage()
            );
        }
    }

    public void withdraw(double amount) {

        try {
            validateAmount(amount);

            if (amount > balance) {
                throw new IllegalArgumentException(
                        "Insufficient funds."
                );
            }

            balance -= amount;

            String transaction = String.format(
                    "Withdrawal: -%.2f | Balance: %.2f",
                    amount,
                    balance
            );

            transactionHistory.add(transaction);

            System.out.printf(
                    "Withdrawn %.2f. New balance: %.2f%n",
                    amount,
                    balance
            );

        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Withdrawal :" + e.getMessage()
            );
        }
    }

    public void showTransactionHistory() {

        System.out.println();
        System.out.println("===== TRANSACTION HISTORY =====");

        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        for (int i = 0; i < transactionHistory.size(); i++) {
            System.out.printf(
                    "%d. %s%n",
                    i + 1,
                    transactionHistory.get(i)
            );
        }

        System.out.println("===============================");
    }

    private void validateAmount(double amount) {

        if (!Double.isFinite(amount)) {
            throw new IllegalArgumentException(
                    "Amount must be a valid number."
            );
        }

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be greater than 0."
            );
        }
    }

    private boolean isValidPin(String pin) {

        return pin != null
                && pin.matches("\\d{4}");
    }
}