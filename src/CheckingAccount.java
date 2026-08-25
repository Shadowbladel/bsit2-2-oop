public class CheckingAccount extends Account {

    private double overdraftLimit;

    public CheckingAccount(String accountNumber, String ownerName,
                           double openingBalance, double overdraftLimit) {
        // TODO 1: DONE
        super(accountNumber, ownerName, openingBalance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public String getAccountType() {
        // TODO 2: DONE
        return "CHECKING";
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        // TODO 3: DONE
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than 0");
        }

        double projectedBalance = getBalance() - amount;
        if (projectedBalance < -overdraftLimit) {
            double shortfall = -overdraftLimit - projectedBalance;
            throw new InsufficientFundsException(shortfall);
        }

        applyWithdrawal(amount);
    }
}