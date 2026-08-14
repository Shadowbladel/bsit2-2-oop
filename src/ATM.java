import java.util.InputMismatchException;
import java.util.Scanner;

public class ATM {

    public static void main(String[] args) {

        try (Scanner input = new Scanner(System.in)) {

            Account account = createAccount();

            if (!authenticateUser(input, account)) {
                System.out.println(
                        "Too many incorrect attempts."
                );
                System.out.println(
                        "Access denied. Goodbye!"
                );
                return;
            }

            runATM(input, account);

        } catch (IllegalArgumentException e) {


            System.out.println(
                    "Account : " + e.getMessage()
            );

        } catch (Exception e) {

            System.out.println(
                    "Unexpected : " + e.getMessage()
            );
        }
    }

    private static Account createAccount() {

        // Example account with a 4-digit PIN.
        return new Account(
                "Vincent Dayondon",
                1000.00,
                "1234"
        );
    }

    private static boolean authenticateUser(
            Scanner input,
            Account account
    ) {

        final int maxAttempts = 3;

        for (int attempt = 1;
             attempt <= maxAttempts;
             attempt++) {

            System.out.print("Enter 4-digit PIN: ");

            String enteredPin = input.nextLine().trim();

            if (account.verifyPin(enteredPin)) {
                System.out.println(
                        "PIN accepted. Welcome, "
                                + account.getOwner() + "!"
                );

                return true;
            }

            int remainingAttempts = maxAttempts - attempt;

            if (remainingAttempts > 0) {
                System.out.println(
                        "Incorrect PIN. Attempts remaining: "
                                + remainingAttempts
                );
            }
        }

        return false;
    }

    private static void runATM(
            Scanner input,
            Account account
    ) {

        boolean running = true;

        while (running) {

            displayMenu();

            int choice = readInteger(
                    input,
                    "Choose an option: "
            );

            switch (choice) {

                case 1 -> checkBalance(account);

                case 2 -> depositMoney(input, account);

                case 3 -> withdrawMoney(input, account);

                case 4 ->
                        account.showTransactionHistory();

                case 5 -> {
                    System.out.println(
                            "Thank you for using CLI ATM!"
                    );

                    running = false;
                }

                default -> System.out.println(
                        "Invalid option. Please choose 1-5."
                );
            }
        }
    }

    private static void displayMenu() {

        System.out.println();
        System.out.println("===== WELCOME TO CLI ATM =====");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transaction History");
        System.out.println("5. Exit");
    }

    private static void checkBalance(Account account) {

        System.out.println(
                "Account Holder: " + account.getOwner()
        );

        System.out.printf(
                "Current Balance: %.2f%n",
                account.getBalance()
        );
    }

    private static void depositMoney(
            Scanner input,
            Account account
    ) {

        double amount = readDouble(
                input,
                "Enter amount to deposit: "
        );

        account.deposit(amount);
    }

    private static void withdrawMoney(
            Scanner input,
            Account account
    ) {

        double amount = readDouble(
                input,
                "Enter amount to withdraw: "
        );

        account.withdraw(amount);
    }

    private static int readInteger(
            Scanner input,
            String message
    ) {

        while (true) {

            System.out.print(message);

            try {
                return Integer.parseInt(
                        input.nextLine().trim()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a whole number."
                );
            }
        }
    }

    private static double readDouble(
            Scanner input,
            String message
    ) {

        while (true) {

            System.out.print(message);

            try {
                return Double.parseDouble(
                        input.nextLine().trim()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid amount. Please enter a number."
                );
            }
        }
    }
}