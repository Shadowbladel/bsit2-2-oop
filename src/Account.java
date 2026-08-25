public abstract class Account {

    // TODO 1: DONE
    private String accountNumber;
    private String ownerName;
    private double balance;

    public Account(String accountNumber, String ownerName, double openingBalance) {
        // TODO 2: DONE
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Account number is required");
        }
        if (ownerName == null || ownerName.isBlank()) {
            throw new IllegalArgumentException("Owner name is required");
        }
        if (openingBalance < 0) {
            throw new IllegalArgumentException("Opening balance cannot be negative");
        }

        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = openingBalance;
    }

    // TODO 3: DONE
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        // TODO 4: DONE
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than 0");
        }
        balance = balance + amount;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        // TODO 5: DONE
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than 0");
        }
        if (amount > balance) {
            throw new InsufficientFundsException(amount - balance);
        }
        balance = balance - amount;
    }

    // TODO 6:
    public abstract String getAccountType();

    // TODO 7: DONE
    protected void applyWithdrawal(double amount) {
        balance = balance - amount;
    }

    @Override
    public String toString() {
        return getAccountType() + " " + accountNumber + " (" + ownerName + ")";
    }
}