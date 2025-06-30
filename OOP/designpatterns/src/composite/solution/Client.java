package composite.solution;

import composite.solution.computer.Body;
import composite.solution.computer.Computer;
import composite.solution.computer.Keyboard;
import composite.solution.computer.Monitor;

public class Client {

    public static void main(String[] args) {
        Body body = new Body(100, 70);
        Keyboard keyboard = new Keyboard(5, 2);
        Monitor monitor = new Monitor(50, 30);

        Computer computer = new Computer();
        computer.addDevice(body);
        computer.addDevice(keyboard);
        computer.addDevice(monitor);

        System.out.printf("Computer Power = %dW\n", computer.getPower());
        System.out.printf("Computer Price = %d만원", computer.getPrice());
    }
}
