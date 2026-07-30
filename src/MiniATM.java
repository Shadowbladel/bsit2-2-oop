import java.util.Scanner;

public class MiniATM {
    // Modifiers: 'private' protects the UI components
    private BankAccount account;
    private Scanner input;

    public MiniATM() {
        // Creates a new BankAccount object starting with 1000.00
        this.account = new BankAccount(1000.00);
        this.input = new Scanner(System.in);
    }

    public static void main(String[] args) {
        MiniATM atm = new MiniATM();
        atm.start();
    }

    // The main loop that runs the program
    public void start() {
        System.out.println("===========================");
        System.out.println("  WELCOME TO THE MINI ATM");
        System.out.println("===========================");
        boolean running = true;

        while (running) {
            printMenu();
            String choice = input.nextLine().trim();

            switch (choice) {
                case "1":
                    handleDeposit();
                    break;
                case "2":
                    handleWithdraw();
                    break;
                case "3":
                    handleCheckBalance();
                    break;
                case "4":
                    running = false;
                    System.out.println("\nThank you for using the Mini ATM. Goodbye!");
                    break;
                default:
                    System.out.println("\n[!] Please choose a number from 1 to 4.\n");
            }
        }
    }

    private void printMenu() {
        System.out.println("Current options:");
        System.out.println("  [1] Deposit");
        System.out.println("  [2] Withdraw");
        System.out.println("  [3] Check balance");
        System.out.println("  [4] Exit");
        System.out.print("Enter your choice: ");
    }

    private void handleDeposit() {
        System.out.print("Enter amount to deposit: ");
        String line = input.nextLine().trim();

        try {
            double amount = Double.parseDouble(line);
            account.deposit(amount);
            System.out.printf("Deposited PHP %.2f. New balance: PHP %.2f%n", amount, account.getBalance());
        } catch (NumberFormatException e) {
            System.out.println("[!] Please enter a valid number.");
        } catch (InvalidAmountException e) {
            System.out.println("[!] " + e.getMessage());
        } finally {
            System.out.println("-- transaction finished --\n");
        }
    }

    private void handleWithdraw() {
        System.out.print("Enter amount to withdraw: ");
        String line = input.nextLine().trim();

        try {
            double amount = Double.parseDouble(line);
            account.withdraw(amount);
            System.out.printf("Withdrew PHP %.2f. New balance: PHP %.2f%n", amount, account.getBalance());
        } catch (NumberFormatException e) {
            System.out.println("[!] Please enter a valid number.");
        } catch (InvalidAmountException | InsufficientFundsException e) {
            // Multi-catch for handling custom exceptions the same way
            System.out.println("[!] " + e.getMessage());
        } finally {
            System.out.println("-- transaction finished --\n");
        }
    }

    private void handleCheckBalance() {
        System.out.printf("%nYour current balance is: PHP %.2f%n%n", account.getBalance());
    }
}