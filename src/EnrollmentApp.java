import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class EnrollmentApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();
        HashMap<String, ArrayList<String>> enrollments = new HashMap<>();

        String[] validPrograms = {"BSIT", "BSCS"};
        int choice = -1;

        while (choice != 0) {
            printMenu();
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                choice = -1;
            }

            switch (choice) {
                case 1:
                    System.out.println("--- REGISTER STUDENT ---");
                    System.out.print("Student ID : ");
                    String id = sc.nextLine();
                    System.out.print("Full Name  : ");
                    String name = sc.nextLine();


                    String program = "";
                    boolean validProg = false;
                    while (!validProg) {
                        System.out.print("Program (BSIT/BSCS): ");
                        program = sc.nextLine().toUpperCase();
                        for (String p : validPrograms) {
                            if (p.equals(program)) {
                                validProg = true;
                                break;
                            }
                        }
                        if (!validProg) {
                            System.out.println("Invalid program. Must be BSIT or BSCS.");
                        }
                    }

                    int yearLevel = 0;
                    while (yearLevel < 1 || yearLevel > 4) {
                        System.out.print("Year Level (1-4): ");
                        try {
                            yearLevel = Integer.parseInt(sc.nextLine());
                            if (yearLevel < 1 || yearLevel > 4) {
                                System.out.println("Year level must be between class 1 and class 4.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid numerical year level.");
                        }
                    }

                    students.add(new Student(id, name, program, yearLevel));
                    System.out.println("[OK] Student registered successfully!");
                    break;

                case 2:
                    System.out.println("--- ADD COURSE OFFERING ---");
                    System.out.print("Course Code : ");
                    String code = sc.nextLine();
                    System.out.print("Course Title: ");
                    String title = sc.nextLine();

                    int units = 0, capacity = 0;
                    try {
                        System.out.print("Units       : ");
                        units = Integer.parseInt(sc.nextLine());
                        System.out.print("Capacity    : ");
                        capacity = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Units and Capacity must be numeric.");
                        break;
                    }

                    courses.add(new Course(code, title, units, capacity));
                    System.out.println("[OK] Course added successfully!");
                    break;

                case 3:
                    System.out.println("--- ENROLL STUDENT ---");
                    System.out.print("Student ID  : ");
                    String sId = sc.nextLine();
                    System.out.print("Course Code : ");
                    String cCode = sc.nextLine();

                    Student student = findStudent(students, sId);
                    if (student == null) {
                        System.out.println("Student does not exist.");
                        break;
                    }

                    Course course = findCourse(courses, cCode);
                    if (course == null) {
                        System.out.println("Course does not exist.");
                        break;
                    }

                    if (course.isFull()) {
                        System.out.println("Course is full.");
                        break;
                    }

                    enrollments.putIfAbsent(sId, new ArrayList<>());
                    ArrayList<String> studentCourses = enrollments.get(sId);

                    if (studentCourses.contains(cCode)) {
                        System.out.println("Student is already enrolled in " + cCode + ".");
                    } else {
                        studentCourses.add(cCode);
                        course.addOneEnrollee();
                        System.out.println("[OK] " + student.getFullName() + " enrolled in " + cCode + " (" + course.getTitle() + ").");
                    }
                    break;

                case 4:
                    System.out.println("--- ALL STUDENTS ---");
                    if (students.isEmpty()) {
                        System.out.println("No students yet.");
                    } else {
                        for (Student s : students) {
                            System.out.println(s.describe());
                        }
                    }
                    break;

                case 5:
                    System.out.println("--- ALL COURSES ---");
                    if (courses.isEmpty()) {
                        System.out.println("No courses yet.");
                    } else {
                        for (Course c : courses) {
                            System.out.println(c.getCourseCode() + " | " + c.getTitle() + " | " + c.getUnits() + " units | " + c.getEnrolledCount() + "/" + c.getCapacity());
                        }
                    }
                    break;

                case 6:
                    System.out.print("Student ID : ");
                    String loadId = sc.nextLine();
                    Student loadStudent = findStudent(students, loadId);

                    if (loadStudent == null) {
                        System.out.println("Student not found.");
                        break;
                    }

                    System.out.println("STUDENT LOAD: " + loadStudent.getFullName());
                    ArrayList<String> enrolledCodes = enrollments.get(loadId);
                    int totalUnits = 0;

                    if (enrolledCodes == null || enrolledCodes.isEmpty()) {
                        System.out.println("No courses enrolled.");
                    } else {
                        for (String ec : enrolledCodes) {
                            Course lc = findCourse(courses, ec);
                            if (lc != null) {
                                System.out.println(lc.getCourseCode() + "\t" + lc.getTitle() + "\t" + lc.getUnits() + " units");
                                totalUnits += lc.getUnits();
                            }
                        }
                    }
                    System.out.println("Total Units: " + totalUnits);
                    break;

                case 0:
                    System.out.println("Thank you for using the Liceo Enrollment System!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        sc.close();
    }

    static void printMenu() {
        System.out.println("\n=======================================");
        System.out.println("=== LICEO ENROLLMENT SYSTEM (CLI) ===");
        System.out.println("=======================================");
        System.out.println("[1] Register Student");
        System.out.println("[2] Add Course Offering");
        System.out.println("[3] Enroll Student to Course");
        System.out.println("[4] View All Students");
        System.out.println("[5] View All Courses");
        System.out.println("[6] View Student Load (Courses + Total Units)");
        System.out.println("[0] Exit");
        System.out.print("Enter choice: ");
    }

    static Student findStudent(ArrayList<Student> list, String id) {
        for (Student s : list) {
            if (s.getStudentId().equals(id)) {
                return s;
            }
        }
        return null;
    }


    static Course findCourse(ArrayList<Course> list, String code) {
        for (Course c : list) {
            if (c.getCourseCode().equals(code)) {
                return c;
            }
        }
        return null;
    }
}