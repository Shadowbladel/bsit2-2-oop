import java.util.ArrayList;

public class Library {

    ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void listBooks() {

        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        System.out.println("\n===== BOOK LIST =====");

        boolean[] counted = new boolean[books.size()];
        int number = 1;

        for (int i = 0; i < books.size(); i++) {

            if (counted[i]) {
                continue;
            }

            Book current = books.get(i);

            int available = 0;
            int borrowed = 0;

            for (int j = i; j < books.size(); j++) {

                Book compare = books.get(j);

                if (current.getTitle().equalsIgnoreCase(compare.getTitle())
                        && current.getAuthor().equalsIgnoreCase(compare.getAuthor())) {

                    counted[j] = true;

                    if (compare.isBorrowed()) {
                        borrowed++;
                    } else {
                        available++;
                    }
                }
            }

            System.out.print(number + ". "
                    + current.getTitle() + " | "
                    + current.getAuthor() + " | ");

            if (available > 0) {
                System.out.print(available + " Available");
            }

            if (borrowed > 0) {
                if (available > 0)
                    System.out.print(", ");
                System.out.print(borrowed + " Borrowed");
            }

            System.out.println();
            number++;
        }
    }

    public void borrowBook(String title) {

        for (Book book : books) {

            if (book.getTitle().equalsIgnoreCase(title) && !book.isBorrowed()) {

                book.borrow();
                System.out.println("Book borrowed successfully.");
                return;
            }
        }

        System.out.println("Book is not available.");
    }

    public void returnBook(String title) {

        for (Book book : books) {

            if (book.getTitle().equalsIgnoreCase(title) && book.isBorrowed()) {

                book.returnBook();
                System.out.println("Book returned successfully.");
                return;
            }
        }

        System.out.println("No borrowed copy found.");
    }

    public void searchBook(String title) {

        boolean found = false;

        for (Book book : books) {

            if (book.getTitle().equalsIgnoreCase(title)) {
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Book exists in the library.");
        } else {
            System.out.println("Book not found.");
        }
    }
}