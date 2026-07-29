import java.util.Scanner;

public class ToolBox {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice;

        do {
            printMenu();
            choice = InputHelper.readInt(input, "Choose an option: ");

            switch (choice) {
                case 1: runGreet(input); break;
                case 2: runArea(input); break;
                case 3: runSum(input); break;
                case 4: runSwap(); break;
                case 5: runBoxDemo(); break;
                case 0: System.out.println("Goodbye!"); break;
                default: System.out.println("Invalid option. Please choose a number from 0 to 5.");
            }
            System.out.println();
        } while (choice != 0);

        input.close();
    }


    private static void printMenu() {
        System.out.println("===== JAVA TOOLBOX =====");
        System.out.println("1 - Greet me");
        System.out.println("2 - Area (square or rectangle)");
        System.out.println("3 - Sum of numbers");
        System.out.println("4 - Swap demo (pass-by-value)");
        System.out.println("5 - Box demo (object mutation)");
        System.out.println("0 - Exit");
    }

    private static void runGreet(Scanner input) {
        System.out.print("Enter your name: ");
        String name = input.next();
        System.out.println(ToolBoxOperations.greet(name));
    }

    private static void runArea(Scanner input) {
        int shape = InputHelper.readInt(input, "Sides (1 = square, 2 = rectangle): ");

        if (shape == 1) {
            double side = InputHelper.readPositiveDouble(input, "Enter side length: ");
            System.out.println("Area of square = " + ToolBoxOperations.area(side));
        } else if (shape == 2) {
            double length = InputHelper.readPositiveDouble(input, "Enter length: ");
            double width = InputHelper.readPositiveDouble(input, "Enter width: ");
            System.out.println("Area of rectangle = " + ToolBoxOperations.area(length, width));
        } else {
            System.out.println("Invalid choice. Please choose 1 or 2.");
        }
    }

    private static void runSum(Scanner input) {
        int[] numbers = InputHelper.readIntList(input, "Enter numbers separated by spaces: ");
        System.out.println("Sum of " + InputHelper.toDisplayList(numbers) + " = " + ToolBoxOperations.sum(numbers));
    }

    private static void runSwap() {
        int x = 5, y = 9;
        System.out.println("Before swap: x = " + x + ", y = " + y);
        ToolBoxOperations.swap(x, y);
        System.out.println("After swap: x = " + x + ", y = " + y + " (unchanged - Java is pass-by-value)");
    }

    private static void runBoxDemo() {
        Box box = new Box(10);
        System.out.println("Before: box.value = " + box.getValue());
        ToolBoxOperations.addToBox(box, 25);
        System.out.println("After: box.value = " + box.getValue() + " (changed - the object is shared)");
    }
}