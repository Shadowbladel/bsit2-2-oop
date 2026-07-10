import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        int choice = -1;

        while (choice != 0) {

            System.out.println("\n===== LIBRARY MENU =====");
            System.out.println("1. Add Book");
            System.out.println("2. List Books");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Search Book");
            System.out.println("0. Exit");

            System.out.print("Enter choice: ");

            try {

                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    case 1:

                        String title;
                        String author;
                        int quantity;

                        do {
                            System.out.print("Enter title: ");
                            title = sc.nextLine();

                            if (title.trim().isEmpty()) {
                                System.out.println("Title cannot be empty.");
                            }

                        } while (title.trim().isEmpty());

                        do {
                            System.out.print("Enter author: ");
                            author = sc.nextLine();

                            if (author.trim().isEmpty()) {
                                System.out.println("Author cannot be empty.");
                            }

                        } while (author.trim().isEmpty());

                        while (true) {
                            try {
                                System.out.print("How many books: ");
                                quantity = sc.nextInt();
                                sc.nextLine();

                                if (quantity <= 0) {
                                    System.out.println("Quantity must be greater than 0.");
                                } else {
                                    break;
                                }

                            } catch (InputMismatchException e) {
                                System.out.println("Please enter a valid number.");
                                sc.nextLine();
                            }
                        }

                        for (int i = 0; i < quantity; i++) {
                            library.addBook(new Book(title, author));
                        }

                        System.out.println(quantity + " book(s) added successfully.");
                        break;

                    case 2:
                        library.listBooks();
                        break;

                    case 3:
                        System.out.print("Enter title: ");
                        library.borrowBook(sc.nextLine());
                        break;

                    case 4:
                        System.out.print("Enter title: ");
                        library.returnBook(sc.nextLine());
                        break;

                    case 5:
                        System.out.print("Enter title: ");
                        library.searchBook(sc.nextLine());
                        break;

                    case 0:
                        System.out.println("Thank you for using the Library System!");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (InputMismatchException e) {

                System.out.println("Numbers only.");
                sc.nextLine();

            } catch (Exception e) {

                System.out.println("Something went wrong.");
            }
        }

        sc.close();
    }
}