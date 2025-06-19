package command.problem.step1;

import command.problem.step1.button.Button;
import command.problem.step1.lamp.Lamp;

public class Client {

    public static void main(String[] args) {
        Lamp lamp = new Lamp();
        Button button = new Button(lamp);

        button.press();
    }
}
