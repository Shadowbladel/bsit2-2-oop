import java.util.Scanner;

public class ATMApp {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ATMService atm = new ATMService();

        Account account = new SavingsAccount("SA-1001", "Vincent C. Dayondon", 5000.0, 0.02);

        boolean running = true;
        while (running) {
            printHeader(account);
            printMenu();
            System.out.print("Choose an option: ");

            try {
                int choice = Integer.parseInt(input.nextLine());

                switch (choice) {
                    case 1:
                        System.out.printf("Balance: PHP %.2f%n", account.getBalance());
                        break;
                    case 2:
                        // TODO 1: Done
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = Double.parseDouble(input.nextLine());
                        atm.deposit(account, depositAmount);
                        System.out.printf("New balance: PHP %.2f%n", account.getBalance());
                        break;
                    case 3:
                        // TODO 2: DONE
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = Double.parseDouble(input.nextLine());
                        account.withdraw(withdrawAmount);
                        System.out.printf("Withdrew PHP %.2f. New balance: PHP %.2f%n",
                                withdrawAmount, account.getBalance());
                        break;
                    case 4:
                        // TODO 3: DONE
                        double total = atm.depositAll(account, 100, 250.5, 300);
                        System.out.printf("Total deposited: PHP %.2f. New balance: PHP %.2f%n",
                                total, account.getBalance());
                        break;
                    case 5:
                        // TODO 4: DONE
                        System.out.printf("Balance before: PHP %.2f%n", account.getBalance());
                        atm.tryToReplace(account);
                        atm.addBonus(account, 50);
                        System.out.printf("Balance after: PHP %.2f%n", account.getBalance());
                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Choose 0 to 5.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please type a number, not letters.");
            } catch (InsufficientFundsException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                System.out.println("---------------------------------");
            }
        }

        input.close();
        System.out.println("Thank you for using Liceo ATM!");
    }

    private static void printHeader(Account account) {
        System.out.println("=================================");
        System.out.println("      LICEO ATM MACHINE");
        System.out.println("=================================");
        // TODO 5: DONE
        System.out.printf("Account : %s  (%s)%n", account.getAccountNumber(), account.getOwnerName());
        System.out.printf("Type    : %s%n", account.getAccountType());
        System.out.printf("Balance : PHP %.2f%n", account.getBalance());
    }

    private static void printMenu() {
        System.out.println("[1] Check Balance");
        System.out.println("[2] Deposit");
        System.out.println("[3] Withdraw");
        System.out.println("[4] Deposit Multiple Amounts");
        System.out.println("[5] Pass-by-Value Demo");
        System.out.println("[0] Exit");
    }
}