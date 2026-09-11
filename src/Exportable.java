public interface Exportable {

    // TODO 1 done
    String toCsv();

    // TODO 2 done
    default void printExport() {
        System.out.println(toCsv());
    }
}