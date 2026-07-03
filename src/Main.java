import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int maxStudents = 10;

        int[] studentIds = new int[maxStudents];
        String[] fullNames = new String[maxStudents];
        int[] ages = new int[maxStudents];
        String[] courses = new String[maxStudents];
        double[] grades = new double[maxStudents];
        boolean[] enrolledStatuses = new boolean[maxStudents];

        int studentCount = 0;
        boolean exit = false;

        // --- MAIN PROGRAM LOOP ---
        while (!exit) {
            System.out.println("========================================");
            System.out.println("       STUDENT INFORMATION SYSTEM       ");
            System.out.println("========================================");
            System.out.println("[1] Add Student");
            System.out.println("[2] View All Students");
            System.out.println("[3] Search Student by ID");
            System.out.println("[4] View Statistics");
            System.out.println("[5] Exit");
            System.out.print("Enter choice: ");

            // Validate menu selection input
            if (!scanner.hasNextInt()) {
                System.out.println("\n[Error] Invalid input. Please enter a number.\n");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch (choice) {
                case 1: // --- ADD STUDENT ---
                    if (studentCount >= maxStudents) {
                        System.out.println("[Error] Cannot add more students. Maximum capacity reached.\n");
                        break;
                    }

                    System.out.println("--- ADD NEW STUDENT ---");

                    // 1. Student ID Validation
                    System.out.print("Enter Student ID: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("[Error] Invalid input. Please enter an integer.");
                        System.out.print("Enter Student ID: ");
                        scanner.next();
                    }
                    studentIds[studentCount] = scanner.nextInt();
                    scanner.nextLine();

                    // 2. Full Name Input
                    System.out.print("Enter Full Name: ");
                    fullNames[studentCount] = scanner.nextLine();

                    // 3. Age Validation
                    int age;
                    do {
                        System.out.print("Enter Age: ");
                        while (!scanner.hasNextInt()) {
                            System.out.println("[Error] Invalid input. Please enter an integer.");
                            System.out.print("Enter Age: ");
                            scanner.next();
                        }
                        age = scanner.nextInt();
                        if (age <= 0) {
                            System.out.println("[Error] Invalid age. Must be a positive number.");
                        }
                    } while (age <= 0);
                    ages[studentCount] = age;
                    scanner.nextLine();

                    // 4. Course Input
                    System.out.print("Enter Course: ");
                    courses[studentCount] = scanner.nextLine();

                    // 5. Grade Validation
                    double grade;
                    do {
                        System.out.print("Enter Grade (0-100): ");
                        while (!scanner.hasNextDouble()) {
                            System.out.println("[Error] Invalid input. Please enter a number.");
                            System.out.print("Enter Grade (0-100): ");
                            scanner.next();
                        }
                        grade = scanner.nextDouble();
                        if (grade < 0 || grade > 100) {
                            System.out.println("[Error] Invalid grade. Must be between 0 and 100.");
                        }
                    } while (grade < 0 || grade > 100);
                    grades[studentCount] = grade;

                    // 6. Enrollment Status Validation
                    System.out.print("Is Enrolled? (true/false): ");
                    while (!scanner.hasNextBoolean()) {
                        System.out.println("Invalid input. Please enter 'true' or 'false'.");
                        System.out.print("Is Enrolled? (true/false): ");
                        scanner.next();
                    }
                    enrolledStatuses[studentCount] = scanner.nextBoolean();

                    System.out.println("\n>> Student added successfully!\n");
                    studentCount++;
                    break;

                case 2: // --- VIEW ALL STUDENTS ---
                    System.out.println("-------------------------------- STUDENT RECORDS --------------------------------");
                    if (studentCount == 0) {
                        System.out.println("No student records found.");
                    } else {
                        System.out.printf("%-6s %-18s %-5s %-10s %-8s %-15s\n",
                                "ID", "NAME", "AGE", "COURSE", "GRADE", "STANDING");
                        System.out.println("---------------------------------------------------------------------------------");

                        for (int i = 0; i < studentCount; i++) {
                            String standing;
                            if (grades[i] >= 90) {
                                standing = "Dean's Lister";
                            } else if (grades[i] >= 75) {
                                standing = "Passed";
                            } else {
                                standing = "Failed";
                            }

                            System.out.printf("%-6d %-18s %-5d %-10s %-8.1f %-15s\n",
                                    studentIds[i], fullNames[i], ages[i], courses[i], grades[i], standing);
                        }
                    }
                    System.out.println();
                    break;

                case 3: // --- SEARCH STUDENT BY ID ---
                    System.out.print("Enter Student ID to search: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter an integer.");
                        System.out.print("Enter Student ID to search: ");
                        scanner.next();
                    }
                    int searchId = scanner.nextInt();
                    boolean found = false;

                    for (int i = 0; i < studentCount; i++) {
                        if (studentIds[i] == searchId) {
                            System.out.println("\n--- STUDENT FOUND ---");
                            System.out.println("ID Number : " + studentIds[i]);
                            System.out.println("Name      : " + fullNames[i]);
                            System.out.println("Age       : " + ages[i]);
                            System.out.println("Course    : " + courses[i]);
                            System.out.println("Grade     : " + grades[i]);
                            System.out.println("Enrolled  : " + (enrolledStatuses[i] ? "Yes" : "No"));
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("\n[Notice] Student with ID " + searchId + " was not found.");
                    }
                    System.out.println();
                    break;

                case 4: // --- VIEW STATISTICS ---
                    System.out.println("--- PROGRAM STATISTICS ---");
                    System.out.println("Total Students : " + studentCount);

                    if (studentCount > 0) {
                        double total = 0;
                        double highestGrade = -1;
                        String topStudentName = "";

                        for (int i = 0; i < studentCount; i++) {
                            total += grades[i];
                            if (grades[i] > highestGrade) {
                                highestGrade = grades[i];
                                topStudentName = fullNames[i];
                            }
                        }

                        double average = total / studentCount;
                        System.out.printf("Average Grade  : %.2f\n", average);
                        System.out.printf("Top Student    : %s (Grade: %.1f)\n", topStudentName, highestGrade);
                    } else {
                        System.out.println("Average Grade  : N/A (No records)");
                        System.out.println("Top Student    : N/A (No records)");
                    }
                    System.out.println();
                    break;

                case 5: // --- EXIT PROGRAM ---
                    System.out.println("Thank you for using the Student Information System. Goodbye!");
                    exit = true;
                    break;

                default:
                    System.out.println("[Error] Invalid choice. Please select an option from 1 to 5.\n");
            }
        }
        scanner.close();
    }
}