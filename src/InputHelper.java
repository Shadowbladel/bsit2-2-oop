import java.util.Scanner;

public class InputHelper {
    private InputHelper() {
    }

    public static int readInt(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (input.hasNextInt()) {
                return input.nextInt();
            }
            System.out.println("Please enter a whole number.");
            input.next();
        }
    }


    public static double readPositiveDouble(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (input.hasNextDouble()) {
                double value = input.nextDouble();
                if (value > 0) {
                    return value;
                }
                System.out.println("Please enter a number greater than 0.");
            } else {
                System.out.println("Please enter a valid number.");
                input.next();
            }
        }
    }


    public static int[] readIntList(Scanner input, String prompt) {
        input.nextLine();
        while (true) {
            System.out.print(prompt);
            String line = input.nextLine().trim();

            if (line.isEmpty()) {
                System.out.println("Please enter at least one number.");
                continue;
            }

            String[] tokens = line.split("\\s+");
            try {
                int[] numbers = new int[tokens.length];
                for (int i = 0; i < tokens.length; i++) {
                    numbers[i] = Integer.parseInt(tokens[i]);
                }
                return numbers;
            } catch (NumberFormatException e) {
                System.out.println("Please enter whole numbers only, separated by spaces.");
            }
        }
    }


    public static String toDisplayList(int[] numbers) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            text.append(numbers[i]);
            if (i < numbers.length - 1) {
                text.append(", ");
            }
        }
        return text.toString();
    }
}