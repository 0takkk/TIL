package singleton.solution.synchronizedprinter;

public class SynchronizedPrinter {

    private static SynchronizedPrinter printer = null;

    private SynchronizedPrinter () {}

    public synchronized static SynchronizedPrinter getInstance() {
        if(printer == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {

            }

            printer = new SynchronizedPrinter();
        }

        return printer;
    }

    public void print(String message) {
        System.out.println(message);
    }
}
