package singleton.problem.singlethread;

public class Printer {

    private static Printer printer = null;

    private Printer() {}

    public static Printer getInstance() {
        if(printer == null) {
            printer = new Printer();
        }

        return printer;
    }

    public void print(String message) {
        System.out.println(message);
    }
}
