import java.util.Scanner;

public class Main {

    private static Scanner input = new Scanner(System.in);
    private static PaymentGateway gateway = new PaymentGateway();
    private static int nextId = 1001;

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   LICEO PAY - Payment Gateway (CLI)");
        System.out.println("========================================");
        seedSamplePayments();

        boolean running = true;
        while (running) {
            showMenu();
            String choice = input.nextLine().trim();

            if (choice.equals("1")) {
                makePayment();
            } else if (choice.equals("2")) {
                System.out.println();
                gateway.processAll();
            } else if (choice.equals("3")) {
                findPayment();
            } else if (choice.equals("4")) {
                System.out.printf("%nPayments recorded: %d%n", gateway.count());
                System.out.printf("Total collected  : PHP %.2f%n", gateway.totalCollected());
            } else if (choice.equals("5")) {
                System.out.println();
                System.out.println("Refunding every payment that can be refunded:");
                gateway.refundAll();
            } else if (choice.equals("6")) {
                System.out.println();
                System.out.println("Service fees (the two serviceFee methods):");
                gateway.showServiceFees();
            } else if (choice.equals("0")) {
                running = false;
                System.out.println();
                System.out.println("Salamat! Goodbye.");
            } else {
                System.out.println("Unknown choice. Please pick 0 to 6.");
            }
        }
    }

    private static void showMenu() {
        System.out.println();
        System.out.println("----------------------------------------");
        System.out.println(" 1. Make a payment");
        System.out.println(" 2. Show all receipts");
        System.out.println(" 3. Find a payment by ID");
        System.out.println(" 4. Show total collected");
        System.out.println(" 5. Refund the refundable payments");
        System.out.println(" 6. Compare service fees");
        System.out.println(" 0. Exit");
        System.out.println("----------------------------------------");
        System.out.print("Choice: ");
    }

    private static void makePayment() {
        System.out.println();
        System.out.println("Payment method:  1 = GCash   2 = Maya   3 = Cash");
        System.out.print("Method: ");
        String method = input.nextLine().trim();

        System.out.print("Payer name: ");
        String name = input.nextLine().trim();

        System.out.print("Amount: ");
        double amount = readDouble();

        Payment payment;

        if (method.equals("1")) {
            System.out.print("Mobile number: ");
            String mobile = input.nextLine().trim();
            payment = new GCashPayment(nextId, name, amount, mobile);
        } else if (method.equals("2")) {
            System.out.print("Email address: ");
            String email = input.nextLine().trim();
            payment = new MayaPayment(nextId, name, amount, email);
        } else if (method.equals("3")) {
            payment = new CashPayment(nextId, name, amount);
        } else {
            System.out.println("Unknown method. The payment was not recorded.");
            return;
        }

        gateway.add(payment);
        nextId++;
        System.out.println();
        System.out.println("Recorded:");
        payment.printReceipt();
    }

    private static void findPayment() {
        System.out.print("Enter payment ID: ");
        int id = readInt();
        Payment found = gateway.findById(id);
        if (found == null) {
            System.out.println("No payment found with ID " + id + ".");
        } else {
            System.out.println("Found:");
            found.printReceipt();
        }
    }

    private static int readInt() {
        while (true) {
            String line = input.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("That is not a whole number. Try again: ");
            }
        }
    }

    private static double readDouble() {
        while (true) {
            String line = input.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.print("That is not an amount. Try again: ");
            }
        }
    }

    private static void seedSamplePayments() {
        gateway.add(new GCashPayment(nextId, "Ana", 1500.00, "0917-555-0134"));
        nextId++;
        gateway.add(new MayaPayment(nextId, "Jerome", 899.50, "jerome@liceo.edu.ph"));
        nextId++;
        gateway.add(new CashPayment(nextId, "Liza", 250.00));
        nextId++;
    }
}