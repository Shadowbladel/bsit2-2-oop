public class BankAccount {
    // Modifier: 'private' ensures the balance cannot be changed directly from outside
    private double balance;

    // Constructor to set the initial balance
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Modifier: 'public' allows other classes to read the balance
    public double getBalance() {
        return this.balance;
    }

    // Validation: Checks for negative/zero amounts before depositing
    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be greater than zero.");
        }
        this.balance += amount;
    }

    // Validation: Checks for negative/zero amounts AND insufficient funds
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