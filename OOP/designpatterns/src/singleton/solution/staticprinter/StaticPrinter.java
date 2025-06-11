package singleton.solution.staticprinter;

public class StaticPrinter {

    private static StaticPrinter printer = new StaticPrinter();

    private StaticPrinter() {}

    public static StaticPrinter getInstance() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {

        }

        return printer;
    }

    public void print(String message) {
        System.out.println(message);
    }
}
