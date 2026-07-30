public class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return this.balance;
    }

    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be greater than zero.");
        }
        this.balance += amount;
    }

    public void withdraw(double amount) throws InvalidAmountException, InsufficientFundsException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be greater than zero.");
        }
        if (amount > this.balance) {
            double shortfall = amount - this.balance;
            throw new InsufficientFundsException("Insufficient funds. You are short by PHP " + String.format("%.2f", shortfall), shortfall);
        }
        this.balance -= amount;
    }
}