package singleton.problem.singlethread;

public class User {

    private String namme;

    public User(String namme) {
        this.namme = namme;
    }

    public void print() {
        Printer printer = Printer.getInstance();
        printer.print(this.namme + " print using " + printer.toString() + ".");
    }
}
