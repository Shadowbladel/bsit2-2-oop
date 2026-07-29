public class ToolBoxOperations {


    public static String greet(String name) {
        return "Hello, " + name + "! Welcome to my Java Toolbox.";
    }


    public static double area(double side) {
        return side * side;
    }


    public static double area(double length, double width) {
        return length * width;
    }


    public static int sum(int... numbers) {
        int total = 0;
        for (int n : numbers) {
            total += n;
        }
        return total;
    }


    public static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
        System.out.println(" (inside swap) a = " + a + ", b = " + b);
    }


    public static void addToBox(Box box, int amount) {
        box.setValue(box.getValue() + amount);
    }
}