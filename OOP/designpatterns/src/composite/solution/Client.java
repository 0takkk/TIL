package composite.solution;

import composite.solution.computer.*;

public class Client {

    public static void main(String[] args) {
        Body body = new Body(100, 70);
        Keyboard keyboard = new Keyboard(5, 2);
        Monitor monitor = new Monitor(50, 30);
        Speaker speaker = new Speaker(30, 20);

        Computer computer = new Computer();
        computer.addDevice(body);
        computer.addDevice(keyboard);
        computer.addDevice(monitor);
        computer.addDevice(speaker);

        System.out.printf("Computer Power = %dW\n", computer.getPower());
        System.out.printf("Computer Price = %d만원", computer.getPrice());
    }
}
