import java.util.InputMismatchException;
import java.util.Scanner;

public class GradeTracker {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GradeManager manager = new GradeManager();
        boolean running = true;

        while (running) {
            System.out.println("\n===== GRADE TRACKER =====");
            System.out.println("1. Add student");
            System.out.println("2. View all students");
            System.out.println("3. Class average");
            System.out.println("4. Exit");
            System.out.print("Choice: ");

            int choice = -1;
            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    String name = "";
                    boolean validName = false;

                    while (!validName) {
                        System.out.print("Name: ");
                        name = sc.nextLine().trim();

                        if (name.isEmpty()) {
                            System.out.println("Name cannot be empty. Please try again.");
                        } else if (manager.doesStudentExist(name)) {
                            System.out.println("A student named '" + name + "' is already on the roster. Please enter a different name.");
                        } else {
                            validName = true;
                        }
                    }

                    double grade = -1;
                    boolean validGrade = false;

                    while (!validGrade) {
                        System.out.print("Grade (0-100): ");
                        try {
                            grade = sc.nextDouble();
                            if (grade >= 0 && grade <= 100) {
                                validGrade = true;
                            } else {
                                System.out.println("Grade must be between 0 and 100.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a numeric grade.");
                            sc.next();
                        }
                    }

                    manager.addStudent(new Student(name, grade));
                    System.out.println("Added " + name + "!");
                    break;

                case 2:
                    if (manager.isRosterEmpty()) {
                        System.out.println("No students yet.");
                    } else {
                        System.out.println("\n--- Class Roster ---");
                        for (Student s : manager.getRoster()) {
                            char letter = manager.getLetterGrade(s.getGrade());
                            System.out.println(s.getName() + " - " + s.getGrade() + " (" + letter + ")");
                        }
                    }
                    break;

                case 3:
                    if (manager.isRosterEmpty()) {
                        System.out.println("No students yet.");
                    } else {
                        double average = manager.calculateAverage();
                        char averageLetter = manager.getLetterGrade(average);
                        System.out.printf("Class average: %.2f (%s)%n", average, averageLetter);
                    }
                    break;

                case 4:
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Pick 1-4.");
            }
        }
        sc.close();
    }
}