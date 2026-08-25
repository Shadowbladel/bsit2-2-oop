public class ATMService {

    // ---------- OVERLOADING: same name, different parameter lists ----------

    public void deposit(Account account, double amount) {
        account.deposit(amount);
        System.out.printf("Deposited PHP %.2f%n", amount);
    }

    public void deposit(Account account, double amount, String note) {
        // TODO 1: DONE
        account.deposit(amount);
        System.out.printf("Deposited PHP %.2f (%s)%n", amount, note);
    }

    // ---------- VARARGS: any number of amounts ----------

    public double depositAll(Account account, double... amounts) {
        double total = 0;
        // TODO 2: DONE
        for (double amount : amounts) {
            account.deposit(amount);
            total += amount;
        }
        return total;
    }

    // ---------- PASS-BY-VALUE: mutation vs. reassignment ----------

    public void tryToReplace(Account account) {
        account = new SavingsAccount("XX-000", "Ghost Account", 0, 0);
        System.out.println("Inside the method : " + account);
        // TODO 3: DONE — explain why the account variable in main()
        //         still points to the ORIGINAL account after this returns.
        // The account in this method is only a copy of the one in main().
        // So, changing it to a new account does not change the account in main().
    }

    public void addBonus(Account account, double bonus) {
        account.deposit(bonus);
        // TODO 4: DONE — explain why THIS change IS visible to main(),
        //         even though Java is pass-by-value.
        // Both account variables point to the same account.
        // So when we add the bonus, the original account is also changed.
    }

    // ---------- TRANSFER ----------

    public void transfer(Account from, Account to, double amount)
            throws InsufficientFundsException {
        // TODO 5: DONE — withdraw from 'from', then deposit into 'to'.
        //         Withdraw FIRST so that a failed withdrawal does not
        //         create money out of nowhere.
        from.withdraw(amount);
        to.deposit(amount);
    }
}
