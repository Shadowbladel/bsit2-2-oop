public class SavingsAccount extends Account {

    public static final double MAINTAINING_BALANCE = 500.0;

    private double interestRate;

    public SavingsAccount(String accountNumber, String ownerName,
                          double openingBalance, double interestRate) {
        // TODO 1: DONE
        super(accountNumber, ownerName, openingBalance);
        // TODO 2: DONE
        this.interestRate = interestRate;
    }

    @Override
    public String getAccountType() {
        return "SAVINGS";
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        // TODO 3: DONE
        double remaining = getBalance() - amount;
        if (remaining < MAINTAINING_BALANCE) {
            throw new InsufficientFundsException(MAINTAINING_BALANCE - remaining);
        }
        super.withdraw(amount);
    }

    public double monthlyInterest() {
        // TODO 4: DONE
        return getBalance() * interestRate / 12;
    }
}