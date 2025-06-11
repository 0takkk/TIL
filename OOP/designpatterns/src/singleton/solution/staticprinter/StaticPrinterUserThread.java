package singleton.solution.staticprinter;

public class StaticPrinterUserThread extends Thread {

    public StaticPrinterUserThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        StaticPrinter printer = StaticPrinter.getInstance();
        printer.print(Thread.currentThread().getName() + " print using " + printer.toString() + ".");
    }
}
