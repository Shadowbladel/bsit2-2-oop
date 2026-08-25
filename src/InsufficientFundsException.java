public class InsufficientFundsException extends Exception {

    private final double shortfall;

    public InsufficientFundsException(double shortfall) {
        super("Insufficient funds. You are short by PHP "
                + String.format("%.2f", shortfall));
        // TODO 1: DONE
        this.shortfall = shortfall;
    }

    // TODO 2: DONE
    public double getShortfall() {
        return shortfall;
    }
}