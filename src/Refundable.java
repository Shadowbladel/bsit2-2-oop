public interface Refundable {

    // TODO 1

    String refund();

    // TODO 2
    default void printRefundNotice() {
        System.out.println("   " + refund());
    }
}