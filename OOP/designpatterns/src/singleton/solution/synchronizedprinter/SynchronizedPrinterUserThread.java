package singleton.solution.synchronizedprinter;

public class SynchronizedPrinterUserThread extends Thread {

    public SynchronizedPrinterUserThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        SynchronizedPrinter printer = SynchronizedPrinter.getInstance();
        printer.print(Thread.currentThread().getName() + " print using " + printer.toString() + ".");
    }
}
