package singleton.problem.multithread;

public class Printer {

    private static Printer printer = null;

    private Printer() {}

    public static Printer getInstance() {
        if(printer == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {

            }

            printer = new Printer();
        }

        return printer;
    }

    public void print(String message) {
        System.out.println(message);
    }
}
