package composite.problem;

import composite.problem.computer.Body;
import composite.problem.computer.Computer;
import composite.problem.computer.Keyboard;
import composite.problem.computer.Monitor;

public class Client {

    public static void main(String[] args) {
        Body body = new Body(100, 70);
        Keyboard keyboard = new Keyboard(5, 2);
        Monitor monitor = new Monitor(50, 30);

        Computer computer = new Computer();
        computer.addBody(body);
        computer.addKeyboard(keyboard);
        computer.addMonitor(monitor);

        System.out.printf("Computer Power = %dW\n", computer.getPower());
        System.out.printf("Computer Price = %d만원", computer.getPrice());
    }
}
